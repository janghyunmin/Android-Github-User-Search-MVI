package com.kkjang.search

import coil.request.GlobalLifecycle.currentState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase,
    private val updateUserBookmarkUseCase: UpdateUserBookmarkUseCase
) : MVIViewModel<SearchIntent, SearchState, SearchEffect>() {
    override fun createInitialState(): SearchState = SearchState()

    override suspend fun processIntent(intent: SearchIntent) = when (intent) {
        is SearchIntent.QueryChanged -> updateQuery(intent.query)
        SearchIntent.LoadMore -> loadNextPage()
        SearchIntent.Sync -> syncList()
        is SearchIntent.BookmarkChanged -> updateBookmark(intent.id, intent.hasBookmark)
    }

    private suspend fun updateQuery(query: String) {
        // 화면전환시 화면이 재생성되면서 발생되는 query change 이벤트를 막기 위함
        if (query == currentState { this.query }) return
        setState { copy(query = query, endOfPaginationReached = false) }
        searchUser(LoadType.REFRESH)
    }

    private suspend fun loadNextPage() {
        searchUser(LoadType.APPEND)
    }

    private suspend fun syncList() {
        searchUser(LoadType.SYNC)
    }

    private suspend fun searchUser(loadType: LoadType) {
        val query = currentState { query }
        if (query.isBlank()) {
            cancelLaunchLatestJob("search")
            setState { copy(isLoading = false, bookmarkUserList = listOf()) }
            return
        }
        // 이미 다음 페이지를 불러오고 있거나, 더이상 불러올 페이지가 없는 경우 무시하도록
        if (loadType == LoadType.APPEND) {
            val isLoading = currentState { isLoading }
            val endOfPage = currentState { endOfPaginationReached }
            if (isLoading || endOfPage) return
        }
        launchLatest("search") {
            setState { copy(isLoading = true) }
            // 입력 디바운스 효과
            if (loadType != LoadType.SYNC) delay(500)
            val param = SearchUserUseCase.Params(query = query, loadType)
            searchUserUseCase(param).onComplete(
                doOnError = { throwable ->
                    showErrorMessage(throwable)
                },
                doOnSuccess = {
                    when (this) {
                        is PagingResult.Success -> {
                            setState {
                                copy(
                                    bookmarkUserList = item.list,
                                    endOfPaginationReached = this@onComplete.endOfPaginationReached
                                )
                            }
                        }
                        is PagingResult.Error -> {
                            setState { copy(bookmarkUserList = item.list) }
                            showErrorMessage(throwable)
                        }
                    }
                })
        }.invokeOnCompletion {
            setState { copy(isLoading = false) }
        }
    }

    private fun showErrorMessage(throwable: Throwable) {
        throwable.message?.let { message ->
            setEffect { SearchEffect.ShowToastMessageEffect(message) }
        }
    }

    private suspend fun updateBookmark(id: Long, hasBookmark: Boolean) {
        val param = UpdateUserBookmarkUseCase.Params(id, hasBookmark)
        updateUserBookmarkUseCase(param).onCompleteSuspend {
            syncList()
        }
    }
}
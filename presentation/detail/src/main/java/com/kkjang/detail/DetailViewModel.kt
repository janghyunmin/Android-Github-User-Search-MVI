package com.kkjang.detail

import coil.request.GlobalLifecycle.currentState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserBookmarkUseCase: UpdateUserBookmarkUseCase
) : MVIViewModel<DetailIntent, DetailState, DetailEffect>() {
    override fun createInitialState(): DetailState = DetailState()

    override suspend fun processIntent(intent: DetailIntent) = when (intent) {
        is DetailIntent.Refresh -> refresh(intent.id)
        is DetailIntent.BookmarkChanged -> updateBookmark(intent.hasBookmark)
    }

    private suspend fun refresh(id: Long) {
        val param = GetUserUseCase.Params(id)
        getUserUseCase(param).onComplete {
            setState {
                copy(
                    id = id,
                    name = this@onComplete.name,
                    profileUrl = this@onComplete.profileUrl,
                    webUrl = this@onComplete.webUrl,
                    hasBookmark = this@onComplete.hasBookmark
                )
            }
        }
    }

    private suspend fun updateBookmark(hasBookmark: Boolean) {
        val id = currentState { id }
        val param = UpdateUserBookmarkUseCase.Params(id, hasBookmark)
        updateUserBookmarkUseCase(param).onCompleteSuspend {
            refresh(id)
        }
    }
}
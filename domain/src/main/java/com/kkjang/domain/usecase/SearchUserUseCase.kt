package com.kkjang.domain.usecase

import com.kkjang.domain.CoroutineDispatcherProvider
import com.kkjang.domain.DomainListModel
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.model.LoadType
import com.kkjang.domain.model.PagingResult
import com.kkjang.domain.repository.SearchRepository

class SearchUserUseCase(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val searchRepository: SearchRepository
) : IOUseCase<SearchUserUseCase.Params, PagingResult<DomainListModel<BookmarkUser>>>(
    coroutineDispatcherProvider
) {
    override suspend fun execute(
        params: Params
    ): PagingResult<DomainListModel<BookmarkUser>> = with(params) {
        return searchRepository.fetchSearchUser(query, loadType)
    }

    data class Params(
        val query: String,
        val loadType: LoadType
    )
}
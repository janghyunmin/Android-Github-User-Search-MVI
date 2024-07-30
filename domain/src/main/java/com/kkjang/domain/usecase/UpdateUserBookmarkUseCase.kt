package com.kkjang.domain.usecase

import com.kkjang.domain.CoroutineDispatcherProvider
import com.kkjang.domain.None
import com.kkjang.domain.repository.SearchRepository

class UpdateUserBookmarkUseCase(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val searchRepository: SearchRepository
) : IOUseCase<UpdateUserBookmarkUseCase.Params, None>(coroutineDispatcherProvider) {
    override suspend fun execute(params: Params): None = with(params) {
        return searchRepository.updateBookmark(id, hasBookmark)
    }

    data class Params(
        val id: Long,
        val hasBookmark: Boolean
    )
}
package com.kkjang.domain.usecase

import com.kkjang.domain.CoroutineDispatcherProvider
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.repository.SearchRepository

class GetUserUseCase(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val searchRepository: SearchRepository
) : IOUseCase<GetUserUseCase.Params, BookmarkUser>(coroutineDispatcherProvider) {
    override suspend fun execute(params: Params): BookmarkUser {
        return searchRepository.getUser(params.id)
    }

    @JvmInline
    value class Params(val id: Long)
}
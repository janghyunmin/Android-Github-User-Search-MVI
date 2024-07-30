package com.kkjang.data.repository

import com.kkjang.domain.DomainListModel
import com.kkjang.domain.None
import com.kkjang.domain.datasource.SearchDataSource
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.model.LoadType
import com.kkjang.domain.model.PagingResult
import com.kkjang.domain.repository.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun fetchSearchUser(
        query: String,
        loadType: LoadType
    ): PagingResult<DomainListModel<BookmarkUser>> {
        return searchDataSource.fetchSearchUser(query, loadType)
    }

    override suspend fun updateBookmark(id: Long, hasBookmark: Boolean): None {
        return searchDataSource.updateBookmark(id, hasBookmark)
    }

    override suspend fun getUser(id: Long): BookmarkUser {
        return searchDataSource.getUser(id)
    }
}
package com.kkjang.domain.datasource

import com.kkjang.domain.DomainListModel
import com.kkjang.domain.None
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.model.LoadType
import com.kkjang.domain.model.PagingResult

interface SearchDataSource {
    suspend fun fetchSearchUser(query: String, loadType: LoadType): PagingResult<DomainListModel<BookmarkUser>>
    suspend fun updateBookmark(id: Long, hasBookmark: Boolean): None
    suspend fun getUser(id: Long): BookmarkUser
}
package com.kkjang.domain.datasource

import com.kkjang.domain.DomainListModel
import com.kkjang.domain.None
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.model.RemoteKey
import com.kkjang.domain.model.User

interface UserDataBase {
    suspend fun addUserList(query: String, page: Int, perPage: Int, items: List<User>): None
    suspend fun getUserList(query: String): DomainListModel<BookmarkUser>
    suspend fun getUser(id: Long): BookmarkUser
    suspend fun deleteUserListByQuery(query: String): None
    suspend fun addBookmark(id: Long): None
    suspend fun deleteBookmark(id: Long): None
    suspend fun getRemoteKey(query: String): RemoteKey
    suspend fun deleteRemoteKey(query: String): None
    suspend fun updateRemoteKey(query: RemoteKey): None
}
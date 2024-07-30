package com.kkjang.data.local

import com.kkjang.data.local.dao.BookmarkDao
import com.kkjang.data.local.dao.RemoteKeyDao
import com.kkjang.data.local.dao.UserDao
import com.kkjang.data.mapper.BookmarkUserEntityListMapper
import com.kkjang.data.mapper.BookmarkUserEntityMapper
import com.kkjang.data.mapper.RemoteKeyEntityMapper
import com.kkjang.data.model.entity.BookmarkEntity
import com.kkjang.data.model.entity.toEntity
import com.kkjang.domain.DomainListModel
import com.kkjang.domain.None
import com.kkjang.domain.datasource.UserDataBase
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.model.RemoteKey
import com.kkjang.domain.model.User
import javax.inject.Inject

internal class UserDataBaseImpl @Inject constructor(
    private val userDao: UserDao,
    private val bookmarkDao: BookmarkDao,
    private val remoteKeyDao: RemoteKeyDao
) : UserDataBase {
    override suspend fun addUserList(
        query: String,
        page: Int,
        perPage: Int,
        items: List<User>
    ): None {
        val start: Long = page.toLong() * perPage
        val entityList = items.withIndex().map { item ->
            item.value.toEntity(query = query, index = start.plus(item.index))
        }
        userDao.insertAll(entityList)
        return None
    }

    override suspend fun getUserList(query: String): DomainListModel<BookmarkUser> {
        return userDao.getAll(query).let(BookmarkUserEntityListMapper::mapDomainList)
    }

    override suspend fun getUser(id: Long): BookmarkUser {
        return userDao.getUser(id)?.let(BookmarkUserEntityMapper::mapDomain)
            ?: throw IllegalStateException("$id User Not Found!")
    }

    override suspend fun deleteUserListByQuery(query: String): None {
        userDao.clear(query)
        return None
    }

    override suspend fun addBookmark(id: Long): None {
        val entity = BookmarkEntity(
            id = id,
            updateTimeStamp = System.currentTimeMillis()
        )
        bookmarkDao.insert(entity)
        return None
    }

    override suspend fun deleteBookmark(id: Long): None {
        bookmarkDao.delete(id)
        return None
    }

    override suspend fun getRemoteKey(query: String): RemoteKey {
        val key = remoteKeyDao.getNextKey(query)?.let(RemoteKeyEntityMapper::mapDomain)
        return key ?: RemoteKey(
            query = query,
            nextPage = 1,
            endOfPaginationReached = false
        )
    }

    override suspend fun deleteRemoteKey(query: String): None {
        remoteKeyDao.delete(query)
        return None
    }

    override suspend fun updateRemoteKey(query: RemoteKey): None {
        val entity = query.toEntity()
        remoteKeyDao.insert(entity)
        return None
    }
}
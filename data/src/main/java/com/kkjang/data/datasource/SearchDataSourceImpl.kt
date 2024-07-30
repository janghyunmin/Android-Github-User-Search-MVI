package com.kkjang.data.datasource

import com.kkjang.data.mapper.UserResponseMapper
import com.kkjang.data.remote.SearchAPI
import com.kkjang.domain.DomainListModel
import com.kkjang.domain.None
import com.kkjang.domain.datasource.SearchDataSource
import com.kkjang.domain.datasource.UserDataBase
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.domain.model.LoadType
import com.kkjang.domain.model.PagingResult
import com.kkjang.domain.model.RemoteKey
import javax.inject.Inject

internal class SearchDataSourceImpl @Inject constructor(
    private val searchAPI: SearchAPI,
    private val userDataBase: UserDataBase
) : SearchDataSource {
    override suspend fun fetchSearchUser(
        query: String,
        loadType: LoadType
    ): PagingResult<DomainListModel<BookmarkUser>> {
        try {
            // db에 저장된 다음 페이지 정보
            val remoteKey: RemoteKey = when (loadType) {
                LoadType.REFRESH -> {
                    // refresh 이벤트 발생시 key, user entity 정보 초기화
                    userDataBase.deleteRemoteKey(query)
                    userDataBase.deleteUserListByQuery(query)
                    userDataBase.getRemoteKey(query)
                }
                LoadType.APPEND -> {
                    // 다음 페이지 정보 가져오고, 이미 페이지의 끝이라면 바로 저장된 정보 return
                    val remoteKey = userDataBase.getRemoteKey(query)
                    if (remoteKey.endOfPaginationReached) {
                        val item = userDataBase.getUserList(query)
                        return PagingResult.Success(endOfPaginationReached = true, item = item)
                    }
                    remoteKey
                }
                LoadType.SYNC -> {
                    val remoteKey = userDataBase.getRemoteKey(query)
                    val item = userDataBase.getUserList(query)
                    return PagingResult.Success(
                        endOfPaginationReached = remoteKey.endOfPaginationReached,
                        item = item
                    )
                }
            }
            val response = searchAPI.searchUserPerPage(
                query = remoteKey.query,
                page = remoteKey.nextPage,
                perPage = PER_PAGE
            )
            val userList = response.items.map(UserResponseMapper::mapDomain)
            val endOfPaginationReached = userList.size < PER_PAGE
            val newRemoteKey = RemoteKey(
                query = remoteKey.query,
                nextPage = remoteKey.nextPage.plus(1),
                endOfPaginationReached = endOfPaginationReached
            )
            userDataBase.addUserList(
                query = newRemoteKey.query,
                page = remoteKey.nextPage.minus(1),
                perPage = PER_PAGE,
                items = userList
            )
            userDataBase.updateRemoteKey(newRemoteKey)
            val item = userDataBase.getUserList(query)
            return PagingResult.Success(
                endOfPaginationReached = endOfPaginationReached,
                item = item
            )
        } catch (exception: Exception) {
            // 중간 통신중 에러가 발생하게 되면 DB에 있는 값을 표시하기 위함
            val item: DomainListModel<BookmarkUser> = runCatching {
                userDataBase.getUserList(query)
            }.getOrNull() ?: DomainListModel(listOf())
            return PagingResult.Error(exception, item)
        }
    }

    override suspend fun updateBookmark(id: Long, hasBookmark: Boolean): None {
        if (hasBookmark) userDataBase.addBookmark(id)
        else userDataBase.deleteBookmark(id)
        return None
    }

    override suspend fun getUser(id: Long): BookmarkUser {
        return userDataBase.getUser(id)
    }

    companion object {
        private const val PER_PAGE = 10
    }
}
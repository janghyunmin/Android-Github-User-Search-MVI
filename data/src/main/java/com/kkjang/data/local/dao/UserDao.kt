package com.kkjang.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kkjang.data.model.entity.BookmarkUserEntity
import com.kkjang.data.model.entity.UserEntity

@Dao
internal interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<UserEntity>)

    @Query("DELETE FROM user WHERE `query` = :query")
    suspend fun clear(query: String)

    @Query("SELECT user.id, user.name, user.profile_url, user.web_url, (bookmark.id NOT NULL) AS has_bookmark FROM user LEFT JOIN bookmark ON user.id = bookmark.id WHERE `query` = :query ORDER BY user.`index` ASC")
    suspend fun getAll(query: String): List<BookmarkUserEntity>

    @Query("SELECT user.id, user.name, user.profile_url, user.web_url, (bookmark.id NOT NULL) AS has_bookmark FROM user LEFT JOIN bookmark ON user.id = bookmark.id WHERE user.id = :id ORDER BY user.`index` ASC LIMIT 1")
    suspend fun getUser(id: Long): BookmarkUserEntity?
}
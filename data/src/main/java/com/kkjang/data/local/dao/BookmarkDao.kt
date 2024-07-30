package com.kkjang.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kkjang.data.model.entity.BookmarkEntity

@Dao
internal interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BookmarkEntity): Long

    @Query("DELETE FROM bookmark WHERE id = :id")
    suspend fun delete(id: Long)
}
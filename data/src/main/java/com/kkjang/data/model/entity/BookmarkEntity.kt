package com.kkjang.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkjang.data.model.EntityModel

@Entity(
    tableName = "bookmark"
)
internal data class BookmarkEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "update_timestamp") val updateTimeStamp: Long
) : EntityModel
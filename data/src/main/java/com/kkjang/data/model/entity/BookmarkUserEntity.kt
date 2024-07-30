package com.kkjang.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkjang.data.model.EntityModel

@Entity(
    tableName = "bookmark_user"
)
internal data class BookmarkUserEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "profile_url") val profileUrl: String,
    @ColumnInfo(name = "web_url") val webUrl: String,
    @ColumnInfo(name = "has_bookmark") val hasBookmark: Boolean
) : EntityModel
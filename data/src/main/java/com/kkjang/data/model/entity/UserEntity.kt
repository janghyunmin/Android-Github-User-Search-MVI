package com.kkjang.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kkjang.data.model.EntityModel
import com.kkjang.domain.model.User

@Entity(
    tableName = "user",
    indices = [
        Index(value = ["id", "query"], unique = true),
        Index(value = ["query", "index"], unique = true)
    ]
)
internal data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "seq") val seq: Long = 0,
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "profile_url") val profileUrl: String,
    @ColumnInfo(name = "web_url") val webUrl: String,
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "index") val index: Long
) : EntityModel

internal fun User.toEntity(query: String, index: Long): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        profileUrl = profileUrl,
        webUrl = webUrl,
        query = query,
        index = index
    )
}
package com.kkjang.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkjang.data.model.EntityModel
import com.kkjang.domain.model.RemoteKey

@Entity(
    tableName = "remote_key"
)
internal data class RemoteKeyEntity(
    @PrimaryKey @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "next_page") val nextPage: Int,
    @ColumnInfo(name = "end_of_paging") val endOfPaginationReached: Boolean,
) : EntityModel

internal fun RemoteKey.toEntity(): RemoteKeyEntity {
    return RemoteKeyEntity(
        query = query,
        nextPage = nextPage,
        endOfPaginationReached = endOfPaginationReached
    )
}
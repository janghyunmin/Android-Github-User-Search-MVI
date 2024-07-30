package com.kkjang.data.mapper

import com.kkjang.data.model.entity.RemoteKeyEntity
import com.kkjang.domain.model.RemoteKey

internal object RemoteKeyEntityMapper : Mapper<RemoteKeyEntity, RemoteKey> {
    override fun mapDomain(input: RemoteKeyEntity): RemoteKey = with(input) {
        RemoteKey(
            query = query,
            nextPage = nextPage,
            endOfPaginationReached = endOfPaginationReached
        )
    }
}
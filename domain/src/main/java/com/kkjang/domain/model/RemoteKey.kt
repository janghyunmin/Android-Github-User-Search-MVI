package com.kkjang.domain.model

import com.kkjang.domain.DomainModel

data class RemoteKey(
    val query: String,
    val nextPage: Int,
    val endOfPaginationReached: Boolean
) : DomainModel
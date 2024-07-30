package com.kkjang.domain.model

import com.kkjang.domain.DomainModel

data class User(
    val id: Long,
    val name: String,
    val profileUrl: String,
    val webUrl: String
) : DomainModel
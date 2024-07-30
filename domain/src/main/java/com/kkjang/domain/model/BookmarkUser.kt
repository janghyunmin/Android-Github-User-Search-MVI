package com.kkjang.domain.model

import com.kkjang.domain.DomainModel

data class BookmarkUser(
    val id: Long,
    val name: String,
    val profileUrl: String,
    val webUrl: String,
    val hasBookmark: Boolean
) : DomainModel

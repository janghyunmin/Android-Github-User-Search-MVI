package com.kkjang.data.mapper

import com.kkjang.data.model.entity.BookmarkUserEntity
import com.kkjang.domain.model.BookmarkUser

internal object  BookmarkUserEntityMapper : Mapper<BookmarkUserEntity, BookmarkUser> {
    override fun mapDomain(input: BookmarkUserEntity): BookmarkUser = with(input){
        BookmarkUser(
            id = id,
            name = name,
            profileUrl = profileUrl,
            webUrl = webUrl,
            hasBookmark = hasBookmark
        )
    }
}
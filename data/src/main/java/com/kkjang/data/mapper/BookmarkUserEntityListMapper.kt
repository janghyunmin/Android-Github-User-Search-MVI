package com.kkjang.data.mapper

import com.kkjang.data.model.entity.BookmarkUserEntity
import com.kkjang.domain.DomainListModel
import com.kkjang.domain.model.BookmarkUser

internal object BookmarkUserEntityListMapper : ListMapper<BookmarkUserEntity, BookmarkUser> {
    override fun mapDomainList(input: List<BookmarkUserEntity>): DomainListModel<BookmarkUser> {
        return input.mapDomainList { item -> BookmarkUserEntityMapper.mapDomain(item) }
    }
}
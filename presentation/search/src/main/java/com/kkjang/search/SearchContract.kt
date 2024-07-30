package com.kkjang.search

import com.kkjang.core.mvi.ViewEffect
import com.kkjang.core.mvi.ViewIntent
import com.kkjang.core.mvi.ViewState
import com.kkjang.domain.model.BookmarkUser

sealed interface SearchIntent : ViewIntent {
    @JvmInline
    value class QueryChanged(val query: String) : SearchIntent
    object LoadMore : SearchIntent
    object Sync : SearchIntent
    data class BookmarkChanged(val id: Long, val hasBookmark: Boolean) : SearchIntent
}

data class SearchState(
    val query: String = "",
    val bookmarkUserList: List<BookmarkUser> = listOf(),
    val endOfPaginationReached: Boolean = false,
    val isLoading: Boolean = false
) : ViewState

sealed interface SearchEffect : ViewEffect {
    @JvmInline
    value class ShowToastMessageEffect(val message: String) : SearchEffect
}
package com.kkjang.detail

sealed interface DetailIntent : ViewIntent {
    @JvmInline
    value class Refresh(val id: Long) : DetailIntent

    @JvmInline
    value class BookmarkChanged(val hasBookmark: Boolean) : DetailIntent
}

data class DetailState(
    val id: Long = -1,
    val name: String = "",
    val profileUrl: String = "",
    val webUrl: String = "",
    val hasBookmark: Boolean = false
) : ViewState

object DetailEffect : ViewEffect
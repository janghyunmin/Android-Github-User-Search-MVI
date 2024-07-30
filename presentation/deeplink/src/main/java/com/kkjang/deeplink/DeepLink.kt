package com.kkjang.deeplink

import android.net.Uri

sealed interface DeepLink {
    val deepLink: String
    val uri: Uri
        get() = Uri.parse(deepLink)

    object Search : DeepLink {
        override val deepLink: String = "github://search"
    }

    data class Detail(val id: Long) : DeepLink {
        override val deepLink: String = "github://search/detail/$id"
    }
}
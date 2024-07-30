package com.kkjang.data.model.remote

import com.kkjang.data.model.ResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserResponse(
    @SerialName("id") val id: Long,
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val profileUrl: String,
    @SerialName("html_url") val webUrl: String
) : ResponseModel
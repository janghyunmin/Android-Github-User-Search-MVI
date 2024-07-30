package com.kkjang.data.model.remote

import com.kkjang.data.model.ResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchUserResponse(
    @SerialName("items") val items: List<UserResponse>
) : ResponseModel
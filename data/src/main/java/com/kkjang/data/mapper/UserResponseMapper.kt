package com.kkjang.data.mapper

import com.kkjang.data.model.remote.UserResponse
import com.kkjang.domain.model.User

internal object UserResponseMapper : Mapper<UserResponse, User> {
    override fun mapDomain(input: UserResponse): User = with(input) {
        User(
            id = id,
            name = name,
            profileUrl = profileUrl,
            webUrl = webUrl
        )
    }
}
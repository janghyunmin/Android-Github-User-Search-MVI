package com.kkjang.domain.model

import com.kkjang.domain.DomainModel

sealed interface LoadType : DomainModel {
    // Swipe를 통해 Refresh가 필요한 경우
    object REFRESH : LoadType
    // 다음 페이지를 불러와야 하는 경우
    object APPEND : LoadType
    // DB와 SYNC 해야 하는 경우
    object SYNC : LoadType
}
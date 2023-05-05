package com.lab.user.domain.user.vo

enum class UserStatus {
    NOT_AUTHORIZE,
    ACTIVE,
    DORMANCY,
    LEAVE
    ;

    fun isActive(): Boolean =
        this == ACTIVE
}

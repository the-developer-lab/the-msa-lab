package com.lab.user.application.port.out

import com.lab.user.domain.user.vo.Username

fun interface AuthorizationTokenCreatePort {
    fun createAuthorizationToken(username: Username): String =
        this.createAuthorizationToken(username.username)

    fun createAuthorizationToken(username: String): String
}

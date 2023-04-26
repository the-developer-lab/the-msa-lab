package com.lab.user.application.port.`in`

import com.lab.user.application.port.`in`.command.UserRegistrationAuthorizeCommand

fun interface UserRegistrationAuthorizeUseCase {
    fun checkAuthenticationCode(userRegistrationAuthorizeCommand: UserRegistrationAuthorizeCommand): Boolean
}

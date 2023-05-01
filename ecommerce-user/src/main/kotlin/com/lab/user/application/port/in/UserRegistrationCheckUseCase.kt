package com.lab.user.application.port.`in`

import com.lab.user.application.port.`in`.command.UserRegistrationCheckCommand

fun interface UserRegistrationCheckUseCase {
    fun checkAuthenticationCode(userRegistrationCheckCommand: UserRegistrationCheckCommand): Boolean
}

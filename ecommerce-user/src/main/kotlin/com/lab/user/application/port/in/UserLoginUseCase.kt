package com.lab.user.application.port.`in`

import com.lab.user.application.port.`in`.command.UserLoginCommand

fun interface UserLoginUseCase {
    fun login(userLoginCommand: UserLoginCommand): String
}

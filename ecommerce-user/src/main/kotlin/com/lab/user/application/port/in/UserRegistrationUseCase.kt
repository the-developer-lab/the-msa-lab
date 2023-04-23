package com.lab.user.application.port.`in`

import com.lab.user.application.port.`in`.command.UserRegistrationCommand

fun interface UserRegistrationUseCase {
    fun registry(userRegistrationCommand: UserRegistrationCommand): Long
}

package com.lab.user.application.port.`in`

import com.lab.user.application.port.`in`.command.UserRegistrationCommand

fun interface UserRegistrationRegistryUseCase {
    fun registry(userRegistrationCommand: UserRegistrationCommand): Long
}

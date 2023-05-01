package com.lab.user.application.port.out

import com.lab.user.domain.user.User

fun interface UserRegistrationPort {
    fun registry(user: User): Long
}

package com.lab.user.application.port.out

import com.lab.user.domain.user.User

fun interface UserReadPort {
    fun read(username: String): User
}

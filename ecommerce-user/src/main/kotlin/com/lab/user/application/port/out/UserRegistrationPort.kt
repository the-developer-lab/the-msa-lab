package com.lab.user.application.port.out

import com.lab.user.domain.User

interface UserRegistrationPort {
    fun registry(user: User): Long
}

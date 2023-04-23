package com.lab.user.application.port.out

interface UserRegistrationAuthenticationUpdatePort {
    fun updateByEmail(email: String)
}

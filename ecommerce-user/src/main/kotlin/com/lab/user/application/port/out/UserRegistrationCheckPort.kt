package com.lab.user.application.port.out

fun interface UserRegistrationCheckPort {
    fun checkAuthenticationCode(email: String, authenticationCode: String): Boolean
}

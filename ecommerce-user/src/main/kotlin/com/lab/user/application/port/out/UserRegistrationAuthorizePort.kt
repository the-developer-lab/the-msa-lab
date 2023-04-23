package com.lab.user.application.port.out

fun interface UserRegistrationAuthorizePort {
    fun checkAuthenticationCode(email: String, authenticationCode: String): Boolean
}

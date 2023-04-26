package com.lab.user.application.port.out

fun interface UserRegistrationAuthenticationPort {
    fun registryAuthenticationCode(email: String): String
}

package com.lab.user.application.port.out

import com.lab.user.domain.user.vo.Email

fun interface UserRegistrationAuthenticationPort {

    fun registryAuthenticationCode(email: Email): String =
        this.registryAuthenticationCode(email.email)

    fun registryAuthenticationCode(email: String): String
}

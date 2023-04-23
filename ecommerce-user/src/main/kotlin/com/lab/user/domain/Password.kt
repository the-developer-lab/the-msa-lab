package com.lab.user.domain

import org.springframework.security.crypto.password.PasswordEncoder

data class Password(
    val password: String,
) {
    companion object {
        fun encode(password: String, passwordEncoder: PasswordEncoder): Password =
            Password(passwordEncoder.encode(password))
    }
}

package com.lab.user.domain.user.vo

import com.lab.user.global.error.PasswordMisMatchException
import org.springframework.security.crypto.password.PasswordEncoder

data class Password(
    val password: String,
) {
    fun matches(passwordEncoder: PasswordEncoder, otherPassword: String) {
        if (!passwordEncoder.matches(otherPassword, password)) {
            throw PasswordMisMatchException()
        }
    }

    companion object {
        fun encode(password: String, passwordEncoder: PasswordEncoder): Password =
            Password(passwordEncoder.encode(password))
    }
}

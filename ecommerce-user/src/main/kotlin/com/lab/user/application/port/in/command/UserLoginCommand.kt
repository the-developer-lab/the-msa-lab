package com.lab.user.application.port.`in`.command

import com.lab.user.domain.Password
import com.lab.user.domain.Username
import com.lab.user.domain.userdetails.CustomUserDetails
import org.springframework.security.crypto.password.PasswordEncoder

data class UserLoginCommand(
    val username: String,
    val password: String,
) {
    fun toUserDetails(passwordEncoder: PasswordEncoder): CustomUserDetails =
        CustomUserDetails(
            username = Username(this.username),
            password = Password(this.password)
        )
}

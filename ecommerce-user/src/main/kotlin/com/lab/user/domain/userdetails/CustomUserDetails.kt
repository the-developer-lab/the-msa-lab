package com.lab.user.domain.userdetails

import com.lab.user.domain.Password
import com.lab.user.domain.Username

data class CustomUserDetails(
    val username: Username,
    val password: Password,
) {
}
package com.lab.user.domain.userdetails

import com.lab.user.domain.user.vo.Password
import com.lab.user.domain.user.vo.Username

data class CustomUserDetails(
    val username: Username,
    val password: Password,
)

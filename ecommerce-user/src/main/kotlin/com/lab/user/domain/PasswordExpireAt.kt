package com.lab.user.domain

import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.ZoneId

data class PasswordExpireAt(
    val passwordExpireAt: LocalDate = LocalDate.now(ZoneId.of("Asia/Seoul")).plusYears(3),
)
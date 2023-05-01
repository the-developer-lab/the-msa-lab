package com.lab.user.domain.user.vo

import java.time.LocalDate
import java.time.ZoneId

data class PasswordExpireAt(
    val passwordExpireAt: LocalDate = LocalDate.now(ZoneId.of("Asia/Seoul")).plusYears(3),
)

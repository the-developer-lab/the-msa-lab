package com.lab.user.domain.mail.vo

import java.time.LocalDateTime
import java.time.ZoneId

data class TransactionAt(
    val transactionAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul")),
)

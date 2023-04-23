package com.lab.user.domain.mail

import java.time.LocalDateTime

data class MailOutbox(
    var outboxStatus: OutboxStatus,
    val mailPayload: MailPayload,
    val mailType: MailType,
    val transactionAt: TransactionAt,
    var publishAt: LocalDateTime? = null,
)

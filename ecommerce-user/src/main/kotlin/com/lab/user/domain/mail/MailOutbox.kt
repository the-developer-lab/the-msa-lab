package com.lab.user.domain.mail

import com.lab.user.domain.mail.vo.MailOutboxStatus
import com.lab.user.domain.mail.vo.MailOutboxType
import com.lab.user.domain.mail.vo.Payload
import com.lab.user.domain.mail.vo.PublishAt
import com.lab.user.domain.mail.vo.TransactionAt
import java.util.UUID

data class MailOutbox(
    var mailOutboxStatus: MailOutboxStatus,
    val payload: Payload,
    val mailOutboxType: MailOutboxType,
    val transactionAt: TransactionAt,
    var publishAt: PublishAt? = null,
    var transactionId: String = UUID.randomUUID().toString(),
)

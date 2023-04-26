package com.lab.user.application.port.out.event

import com.lab.user.domain.mail.MailOutbox
import com.lab.user.domain.mail.MailPayload
import com.lab.user.domain.mail.MailType
import com.lab.user.domain.mail.OutboxStatus
import com.lab.user.domain.mail.TransactionAt
import java.io.Serializable
import java.time.LocalDateTime

data class UserRegistrationEvent(
    var userId: Long = 0L,
    var email: String = "",
    var authenticationCode: String = "",
): Serializable {

    fun toMailOutBoxModel(): MailOutbox =
        MailOutbox(
            outboxStatus = OutboxStatus.STARTED,
            mailPayload = MailPayload(userId, email, authenticationCode),
            mailType = MailType.AUTHORIZATION,
            transactionAt = TransactionAt(),
            publishAt = LocalDateTime.now()
        )

    fun toUserRegistrationMailEvent(transactionId: String): UserRegistrationMailEvent {
        return UserRegistrationMailEvent(
            transactionId = transactionId,
            email = email,
            authenticationCode = authenticationCode
        )
    }
}

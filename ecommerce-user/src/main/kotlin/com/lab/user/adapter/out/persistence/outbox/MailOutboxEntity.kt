package com.lab.user.adapter.out.persistence.outbox

import com.lab.user.domain.mail.MailOutbox
import com.lab.user.domain.mail.OutboxStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.time.LocalDateTime

@Table(name = "mail_outbox")
@Entity
class MailOutboxEntity(

    var outboxStatus: String,
    var payload: String,
    var transactionAt: LocalDateTime,
    var publishAt: LocalDateTime? = null,

    @Version
    var version: Long = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val transactionId: String? = null,
) {
    constructor(outboxStatus: MailOutbox) : this(
        outboxStatus = outboxStatus.outboxStatus.name,
        payload = outboxStatus.mailPayload.payload,
        transactionAt = outboxStatus.transactionAt.transactionAt,
    )

    fun complete(publishAt: LocalDateTime): MailOutboxEntity {
        this.outboxStatus = OutboxStatus.COMPLETED.name
        this.publishAt = publishAt
        return this
    }
}

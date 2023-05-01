package com.lab.user.adapter.out.persistence.mail

import com.lab.user.domain.mail.vo.MailOutboxStatus
import jakarta.persistence.Entity
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
    val transactionId: String? = null,
) {
    fun complete(publishAt: LocalDateTime): MailOutboxEntity {
        this.outboxStatus = MailOutboxStatus.COMPLETED.name
        this.publishAt = publishAt
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MailOutboxEntity

        if (transactionId != other.transactionId) return false

        return true
    }

    override fun hashCode(): Int {
        return transactionId?.hashCode() ?: 0
    }
}

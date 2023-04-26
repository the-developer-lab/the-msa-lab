package com.lab.mail.domain.outbox

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.time.LocalDateTime

@Table(name = "outbox_filter")
@Entity
class OutBoxFilterEntity(

    var transactionId: String,

    @Enumerated(EnumType.STRING)
    var outboxFilterStatus: OutBoxFilterStatus = OutBoxFilterStatus.READY,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Version
    var version: Long = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {
    fun isProcessed(): Boolean {
        return outboxFilterStatus.isProcessed()
    }

    fun updateProcessedStatus(): OutBoxFilterEntity {
        this.outboxFilterStatus = OutBoxFilterStatus.PROCESSED
        return this
    }
}

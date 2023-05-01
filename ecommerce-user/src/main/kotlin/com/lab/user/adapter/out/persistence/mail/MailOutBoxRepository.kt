package com.lab.user.adapter.out.persistence.mail

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock

interface MailOutBoxRepository : JpaRepository<MailOutboxEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    fun findByOutboxStatus(outboxStatus: String): List<MailOutboxEntity>

    @Lock(LockModeType.OPTIMISTIC)
    fun findByTransactionId(transactionId: String): MailOutboxEntity?
}

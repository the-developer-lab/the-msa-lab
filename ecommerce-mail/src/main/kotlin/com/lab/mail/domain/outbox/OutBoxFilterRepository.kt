package com.lab.mail.domain.outbox

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock

interface OutBoxFilterRepository : JpaRepository<MailOutBoxFilterEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    fun findByTransactionId(transactionId: String): MailOutBoxFilterEntity?
}

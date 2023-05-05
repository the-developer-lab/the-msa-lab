package com.lab.user.adapter.out

import com.lab.user.adapter.out.persistence.mail.MailOutBoxRepository
import com.lab.user.application.port.out.MailOutBoxChangeCompletePort
import com.lab.user.global.annotation.Adapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId

@Adapter
class MailOutboxChangeCompleteAdapter(
    private val mailOutBoxRepository: MailOutBoxRepository,
) : MailOutBoxChangeCompletePort {

    @Transactional
    override fun completeMailOutBox(transactionId: String) {
        val mailOutboxEntity = mailOutBoxRepository.findByTransactionId(transactionId)
            ?: throw EntityNotFoundException()
        mailOutboxEntity.complete(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
        mailOutBoxRepository.saveAndFlush(mailOutboxEntity)
    }
}

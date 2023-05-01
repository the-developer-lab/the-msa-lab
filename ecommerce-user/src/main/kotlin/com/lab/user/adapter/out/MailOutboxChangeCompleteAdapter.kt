package com.lab.user.adapter.out

import com.lab.user.adapter.out.persistence.mail.MailOutBoxRepository
import com.lab.user.application.port.out.MailOutBoxChangeCompletePort
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class MailOutboxChangeCompleteAdapter(
    private val mailOutBoxRepository: MailOutBoxRepository,
) : MailOutBoxChangeCompletePort {

    @Transactional
    override fun completeMailOutBox(transactionId: String) {
        println(transactionId)
        val mailOutboxEntity = mailOutBoxRepository.findByTransactionId(transactionId)
            ?: throw EntityNotFoundException()
        mailOutboxEntity.complete(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
        mailOutBoxRepository.saveAndFlush(mailOutboxEntity)
    }
}

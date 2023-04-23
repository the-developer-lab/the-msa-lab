package com.lab.user.adapter.out.persistence

import com.lab.user.adapter.out.persistence.outbox.MailOutBoxRepository
import com.lab.user.adapter.out.persistence.outbox.MailOutboxEntity
import com.lab.user.application.port.out.UserRegistrationEmailOutBoxRegistryPort
import com.lab.user.application.port.out.UserRegistrationEmailOutBoxUpdatePort
import com.lab.user.domain.mail.MailOutbox
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class UserRegistrationEmailAdapter(
    private val mailOutBoxRepository: MailOutBoxRepository,
) : UserRegistrationEmailOutBoxRegistryPort,
    UserRegistrationEmailOutBoxUpdatePort {

    @Transactional
    override fun registryEmailOutBox(mailOutbox: MailOutbox): String =
        mailOutBoxRepository.saveAndFlush(MailOutboxEntity(mailOutbox))
            ?.transactionId
            ?: throw RuntimeException()

    @Transactional
    override fun completeEmailOutBox(transactionId: String) {
        mailOutBoxRepository.findByTransactionId(transactionId)
            ?.let { it.complete(LocalDateTime.now()) }
            ?.let { mailOutBoxRepository.saveAndFlush(it) }
    }
}

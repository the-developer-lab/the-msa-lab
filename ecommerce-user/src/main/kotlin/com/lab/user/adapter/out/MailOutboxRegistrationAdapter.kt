package com.lab.user.adapter.out

import com.fasterxml.jackson.databind.ObjectMapper
import com.lab.user.adapter.out.persistence.mail.MailOutBoxRepository
import com.lab.user.adapter.out.persistence.mail.MailOutboxEntity
import com.lab.user.application.port.out.MailOutBoxRegistrationPort
import com.lab.user.domain.mail.MailOutbox
import com.lab.user.global.annotation.Adapter
import org.springframework.transaction.annotation.Transactional

@Adapter
class MailOutboxRegistrationAdapter(
    private val mailOutBoxRepository: MailOutBoxRepository,
    private val objectMapper: ObjectMapper,
) : MailOutBoxRegistrationPort {

    @Transactional
    override fun registry(mailOutbox: MailOutbox): MailOutboxEntity {
        val mailOutboxEntity = mapToEntity(mailOutbox)
        return mailOutBoxRepository.saveAndFlush(mailOutboxEntity)
    }

    private fun mapToEntity(mailOutbox: MailOutbox) =
        MailOutboxEntity(
            outboxStatus = mailOutbox.mailOutboxStatus.name,
            payload = objectMapper.writeValueAsString(mailOutbox.payload),
            transactionAt = mailOutbox.transactionAt.transactionAt,
            transactionId = mailOutbox.transactionId
        )
}

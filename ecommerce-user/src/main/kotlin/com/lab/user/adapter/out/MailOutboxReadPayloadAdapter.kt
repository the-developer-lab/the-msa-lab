package com.lab.user.adapter.out

import com.fasterxml.jackson.databind.ObjectMapper
import com.lab.user.adapter.out.persistence.mail.MailOutBoxRepository
import com.lab.user.application.port.out.MailOutBoxReadPayloadPort
import com.lab.user.domain.mail.vo.MailOutboxStatus
import com.lab.user.domain.mail.vo.Payload
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MailOutboxReadPayloadAdapter(
    private val mailOutBoxRepository: MailOutBoxRepository,
    private val objectMapper: ObjectMapper,
) : MailOutBoxReadPayloadPort {

    @Transactional(readOnly = true)
    override fun readAllPayloads(): List<Payload> =
        mailOutBoxRepository.findByOutboxStatus(MailOutboxStatus.STARTED.name)
            .map { objectMapper.readValue(it.payload, Payload::class.java) }
            .toList()
}

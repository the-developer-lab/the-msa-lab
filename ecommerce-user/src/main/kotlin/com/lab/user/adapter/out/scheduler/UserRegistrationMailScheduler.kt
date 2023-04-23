package com.lab.user.adapter.out.scheduler

import com.fasterxml.jackson.databind.ObjectMapper
import com.lab.user.adapter.out.persistence.outbox.MailOutBoxRepository
import com.lab.user.application.port.out.event.UserRegistrationEvent
import com.lab.user.domain.mail.MailPayload
import com.lab.user.domain.mail.OutboxStatus
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserRegistrationMailScheduler(
    private val outBoxRepository: MailOutBoxRepository,
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper,
) {

    @Transactional(readOnly = true)
    @Scheduled(
        fixedDelayString = "\${user-service.mail.outbox-scheduler.fixed-rate}",
        initialDelayString = "\${user-service.mail.outbox-scheduler.initial-delay}",
    )
    fun processOutboxMessage() {
        val mailOutboxEntities = outBoxRepository.findByOutboxStatus(OutboxStatus.STARTED.name)
        if (mailOutboxEntities.isNullOrEmpty()) {
            return
        }
        for (mailOutboxEntity in mailOutboxEntities) {
            val mailPayload = objectMapper.readValue(mailOutboxEntity.payload, MailPayload::class.java)
            // 배치로 업데이트 예정
            rabbitTemplate.convertSendAndReceive(
                "", "q.user-registration", UserRegistrationEvent(
                    userId = mailPayload.userId,
                    email = mailPayload.email,
                    authenticationCode = mailPayload.authenticationCode
                )
            )
        }
    }
}

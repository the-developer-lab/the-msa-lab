package com.lab.mail.domain

import com.lab.mail.domain.event.OutBoxProcessedEvent
import com.lab.mail.domain.event.UserRegistrationEvent
import com.lab.mail.domain.template.MailSenderTemplate
import com.lab.mail.global.annotation.EventListener
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate

@EventListener
class ActiveMqListener(
    private val mailSenderTemplate: MailSenderTemplate,
    private val rabbitTemplate: RabbitTemplate
) {

    @RabbitListener(queues = ["q.send-email"])
    fun sendEmail(userRegistrationEvent: UserRegistrationEvent) {
        logger.info("{} 의 회원가입 인증 이벤트를 받았습니다.", userRegistrationEvent.email)
        mailSenderTemplate.sendAuthorizeEmail(
            userRegistrationEvent.transactionId,
            userRegistrationEvent.email,
            userRegistrationEvent.authenticationCode
        )
        rabbitTemplate.convertSendAndReceive("", "q.outbox-processed", OutBoxProcessedEvent(
            userRegistrationEvent.transactionId
        ))
    }

    companion object {
        private val logger = LoggerFactory.getLogger(javaClass)
    }
}

package com.lab.mail.domain

import com.lab.mail.domain.event.OutBoxProcessedEvent
import com.lab.mail.domain.event.UserRegistrationEvent
import com.lab.mail.domain.template.MailSenderTemplate
import com.lab.mail.global.annotation.EventListener
import com.lab.mail.global.constant.Q_POST_REGISTRATION_EMAIL
import com.lab.mail.global.constant.X_POST_REGISTRATION_PROCESSED
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate

@EventListener
class ActiveMqListener(
    private val mailSenderTemplate: MailSenderTemplate,
    private val rabbitTemplate: RabbitTemplate,
) {

    @RabbitListener(queues = [Q_POST_REGISTRATION_EMAIL])
    fun sendEmail(userRegistrationEvent: UserRegistrationEvent) {
        logger.info("{} 의 회원가입 인증 이벤트를 받았습니다.", userRegistrationEvent.email)
        val isSendEmail = mailSenderTemplate.sendAuthorizeEmail(
            userRegistrationEvent.transactionId,
            userRegistrationEvent.email,
            userRegistrationEvent.authenticationCode
        )
        println(userRegistrationEvent.transactionId)
        if (isSendEmail) {
            rabbitTemplate.convertAndSend(
                X_POST_REGISTRATION_PROCESSED, "", OutBoxProcessedEvent(
                    userRegistrationEvent.transactionId
                )
            )
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(javaClass)
    }
}

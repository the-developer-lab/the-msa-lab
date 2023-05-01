package com.lab.user.adapter.`in`.message

import com.lab.user.adapter.out.message.event.MailOutboxResultEvent
import com.lab.user.application.port.out.MailOutBoxChangeCompletePort
import com.lab.user.global.constant.Q_POST_REGISTRATION_EMAIL_PROCESSED
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class MailOutBoxProcessedSubscriber(
    private val outBoxChangeCompletePort: MailOutBoxChangeCompletePort,
) {

    @Async
    @RabbitListener(queues = [Q_POST_REGISTRATION_EMAIL_PROCESSED], containerFactory = "registrationListenerContainerFactory")
    fun subscribe(mailOutboxResultEvent: MailOutboxResultEvent) {
        outBoxChangeCompletePort.completeMailOutBox(mailOutboxResultEvent.transactionId)
    }
}

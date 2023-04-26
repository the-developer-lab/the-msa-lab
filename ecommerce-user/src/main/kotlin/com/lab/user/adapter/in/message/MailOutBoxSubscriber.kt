package com.lab.user.adapter.`in`.message

import com.lab.user.application.port.out.UserRegistrationEmailOutBoxUpdatePort
import com.lab.user.application.port.out.event.OutboxProcessedEvent
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class MailOutBoxSubscriber(
    private val outBoxUpdatePort: UserRegistrationEmailOutBoxUpdatePort,
) : Subscriber {

    @Async
    @RabbitListener(queues = ["q.outbox-processed"], containerFactory = "registrationListenerContainerFactory")
    fun consume(outboxProcessedEvent: OutboxProcessedEvent) {
        outBoxUpdatePort.completeEmailOutBox(outboxProcessedEvent.transactionId)
    }
}

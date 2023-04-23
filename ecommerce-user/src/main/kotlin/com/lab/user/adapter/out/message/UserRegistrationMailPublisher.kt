package com.lab.user.adapter.out.message

import com.lab.user.adapter.out.persistence.UserRegistrationEmailAdapter
import com.lab.user.application.port.out.event.UserRegistrationEvent
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UserRegistrationMailPublisher(
    private val userRegistrationEmailAdapter: UserRegistrationEmailAdapter,
    private val rabbitTemplate: RabbitTemplate,
) : Publisher {


    @Async
    @RabbitListener(queues = ["q.user-registration"], containerFactory = "registrationListenerContainerFactory")
    fun publish(userRegistrationEvent: UserRegistrationEvent) {
        val transactionId =
            userRegistrationEmailAdapter.registryEmailOutBox(userRegistrationEvent.toMailOutBoxModel())
        rabbitTemplate.convertAndSend(
            "x.post-registration",
            "",
            userRegistrationEvent.toUserRegistrationMailEvent(transactionId)
        )
    }
}

package com.lab.user.adapter.out.message

import com.lab.user.adapter.out.message.event.UserRegistrationEvent
import com.lab.user.application.port.out.UserRegistrationPublishPort
import com.lab.user.global.annotation.Adapter
import com.lab.user.global.constant.R_SEND_EMAIL
import com.lab.user.global.constant.X_USER_REGISTRATION
import org.springframework.amqp.rabbit.core.RabbitTemplate

@Adapter
class UserRegistrationPublisher(
    private val rabbitTemplate: RabbitTemplate,
) : UserRegistrationPublishPort {

    override fun publish(userId: Long, email: String, authenticationCode: String) {
        rabbitTemplate.convertAndSend(
            X_USER_REGISTRATION, R_SEND_EMAIL,
            UserRegistrationEvent(
                userId = userId,
                email = email,
                authenticationCode = authenticationCode
            )
        )
    }
}

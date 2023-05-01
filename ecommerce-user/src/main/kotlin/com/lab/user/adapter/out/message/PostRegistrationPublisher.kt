package com.lab.user.adapter.out.message

import com.lab.user.adapter.out.message.event.UserRegistrationMailEvent
import com.lab.user.application.port.out.PostRegistrationPublishPort
import com.lab.user.global.annotation.Adapter
import com.lab.user.global.constant.R_SEND_EMAIL
import com.lab.user.global.constant.X_POST_REGISTRATION
import org.springframework.amqp.rabbit.core.RabbitTemplate

@Adapter
class PostRegistrationPublisher(
    private val rabbitTemplate: RabbitTemplate,
) : PostRegistrationPublishPort {

    override fun publish(email: String, authenticationCode: String, transactionId: String) {
        rabbitTemplate.convertAndSend(
            X_POST_REGISTRATION, R_SEND_EMAIL,
            UserRegistrationMailEvent(
                email = email,
                authenticationCode = authenticationCode,
                transactionId = transactionId,
            )
        )
    }
}

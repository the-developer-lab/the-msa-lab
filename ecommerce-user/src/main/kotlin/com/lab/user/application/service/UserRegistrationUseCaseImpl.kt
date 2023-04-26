package com.lab.user.application.service

import com.lab.user.application.port.`in`.UserRegistrationUseCase
import com.lab.user.application.port.`in`.command.UserRegistrationCommand
import com.lab.user.application.port.out.UserRegistrationAuthenticationPort
import com.lab.user.application.port.out.UserRegistrationPort
import com.lab.user.application.port.out.event.UserRegistrationEvent
import com.lab.user.global.annotation.UseCase
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.security.crypto.password.PasswordEncoder

@UseCase
class UserRegistrationUseCaseImpl(
    private val userRegistrationPort: UserRegistrationPort,
    private val userRegistrationAuthenticationPort: UserRegistrationAuthenticationPort,
    private val passwordEncoder: PasswordEncoder,
    private val rabbitTemplate: RabbitTemplate,
) : UserRegistrationUseCase {

    override fun registry(userRegistrationCommand: UserRegistrationCommand): Long {
        val userId = userRegistrationPort.registry(userRegistrationCommand.toUser(passwordEncoder))
        val authenticationCode =
            userRegistrationAuthenticationPort.registryAuthenticationCode(userRegistrationCommand.email)

        rabbitTemplate.convertSendAndReceive(
            "", "q.user-registration", UserRegistrationEvent(
                userId = userId,
                email = userRegistrationCommand.email,
                authenticationCode = authenticationCode
            )
        )
        return userId
    }
}

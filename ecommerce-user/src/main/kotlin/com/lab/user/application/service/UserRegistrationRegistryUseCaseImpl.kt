package com.lab.user.application.service

import com.lab.user.application.port.`in`.UserRegistrationRegistryUseCase
import com.lab.user.application.port.`in`.command.UserRegistrationCommand
import com.lab.user.application.port.out.UserRegistrationAuthenticationPort
import com.lab.user.application.port.out.UserRegistrationPort
import com.lab.user.application.port.out.UserRegistrationPublishPort
import com.lab.user.global.annotation.UseCase
import org.springframework.security.crypto.password.PasswordEncoder

@UseCase
class UserRegistrationRegistryUseCaseImpl(
    private val userRegistrationPort: UserRegistrationPort,
    private val userRegistrationAuthenticationPort: UserRegistrationAuthenticationPort,
    private val userRegistrationPublishPort: UserRegistrationPublishPort,
    private val passwordEncoder: PasswordEncoder,
) : UserRegistrationRegistryUseCase {

    override fun registry(userRegistrationCommand: UserRegistrationCommand): Long {
        val user = userRegistrationCommand.toDomainModel(passwordEncoder)
        val userId = userRegistrationPort.registry(user)
        val authenticationCode =
            userRegistrationAuthenticationPort.registryAuthenticationCode(user.email)
        userRegistrationPublishPort.publish(userId, user.email, authenticationCode)
        return userId
    }
}

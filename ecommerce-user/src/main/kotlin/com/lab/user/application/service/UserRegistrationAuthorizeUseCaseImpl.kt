package com.lab.user.application.service

import com.lab.user.application.port.`in`.command.UserRegistrationAuthorizeCommand
import com.lab.user.application.port.`in`.UserRegistrationAuthorizeUseCase
import com.lab.user.application.port.out.UserRegistrationAuthenticationUpdatePort
import com.lab.user.application.port.out.UserRegistrationAuthorizePort
import com.lab.user.global.annotation.UseCase

@UseCase
class UserRegistrationAuthorizeUseCaseImpl(
    private val userRegistrationAuthorizePort: UserRegistrationAuthorizePort,
    private val userRegistrationAuthenticationUpdatePort: UserRegistrationAuthenticationUpdatePort,
) : UserRegistrationAuthorizeUseCase {

    override fun checkAuthenticationCode(userRegistrationAuthorizeCommand: UserRegistrationAuthorizeCommand): Boolean {
        val result = userRegistrationAuthorizePort.checkAuthenticationCode(
            userRegistrationAuthorizeCommand.email,
            userRegistrationAuthorizeCommand.authenticationCode
        )
        if (result) {
            userRegistrationAuthenticationUpdatePort.updateByEmail(userRegistrationAuthorizeCommand.email)
        }
        return result
    }
}

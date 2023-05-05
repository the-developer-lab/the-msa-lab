package com.lab.user.application.service

import com.lab.user.application.port.`in`.UserLoginUseCase
import com.lab.user.application.port.`in`.command.UserLoginCommand
import com.lab.user.application.port.out.AuthorizationTokenCreatePort
import com.lab.user.application.port.out.UserReadPort
import com.lab.user.global.annotation.UseCase
import org.springframework.security.crypto.password.PasswordEncoder

@UseCase
class UserLoginUseCaseImpl(
    private val userReadPort: UserReadPort,
    private val passwordEncoder: PasswordEncoder,
    private val authorizationTokenCreatePort: AuthorizationTokenCreatePort,
) : UserLoginUseCase {

    override fun login(userLoginCommand: UserLoginCommand): String {
        val user = userReadPort.read(userLoginCommand.username)
        user.passwordMatches(passwordEncoder, userLoginCommand.password)
        user.verifyUserStatusIsActive()
        return authorizationTokenCreatePort.createAuthorizationToken(user.username)
    }
}

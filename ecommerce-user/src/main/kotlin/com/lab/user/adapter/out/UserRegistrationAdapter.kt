package com.lab.user.adapter.out

import com.lab.user.adapter.out.persistence.user.UserEntity
import com.lab.user.adapter.out.persistence.user.UserRepository
import com.lab.user.application.port.out.UserRegistrationPort
import com.lab.user.domain.user.User
import com.lab.user.global.annotation.DistributedLock
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserRegistrationAdapter(
    private val userRepository: UserRepository,
) : UserRegistrationPort {

    @DistributedLock(prefix = "USER:", key = "#user.username.username")
    @Transactional
    override fun registry(user: User): Long {
        validateUserRegistrationInformation(user)
        val userEntity = UserEntity(user)
        return userRepository.saveAndFlush(userEntity)
            ?.id
            ?: throw IllegalArgumentException()
    }

    private fun validateUserRegistrationInformation(user: User) {
        if (userRepository.existsByUsernameOrEmail(user.username.username, user.email.email)) {
            throw RuntimeException("invalid user data")
        }
    }
}

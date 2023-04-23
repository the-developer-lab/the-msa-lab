package com.lab.user.adapter.out.persistence

import com.lab.user.adapter.out.persistence.user.UserEntity
import com.lab.user.adapter.out.persistence.user.UserRepository
import com.lab.user.application.port.out.UserRegistrationAuthenticationUpdatePort
import com.lab.user.application.port.out.UserRegistrationPort
import com.lab.user.domain.User
import com.lab.user.domain.UserStatus
import com.lab.user.global.annotation.DistributedLock
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserRegistrationAdapter(
    private val userRepository: UserRepository,
) : UserRegistrationPort,
    UserRegistrationAuthenticationUpdatePort {

    @DistributedLock(prefix = "USER:", key = "#user.username.username")
    @Transactional
    override fun registry(user: User): Long {
        validateUserRegistrationInformation(user)
        return userRepository.saveAndFlush(UserEntity(user))
            ?.id
            ?: throw IllegalArgumentException()
    }

    @Transactional
    override fun updateByEmail(email: String) {
        val userEntity: UserEntity = userRepository.findByEmail(email)
            ?: throw EntityNotFoundException("user not found")
        userEntity.userStatus = UserStatus.ACTIVE.name
        userRepository.saveAndFlush(userEntity)
    }

    private fun validateUserRegistrationInformation(user: User) {
        if (userRepository.existsByUsernameOrEmail(user.username.username, user.email.email)) {
            throw RuntimeException("invalid user data")
        }
    }
}

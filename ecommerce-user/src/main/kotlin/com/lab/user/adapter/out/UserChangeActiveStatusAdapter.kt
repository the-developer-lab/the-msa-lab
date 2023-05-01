package com.lab.user.adapter.out

import com.lab.user.adapter.out.persistence.user.UserEntity
import com.lab.user.adapter.out.persistence.user.UserRepository
import com.lab.user.application.port.out.UserChangeActiveStatusPort
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserChangeActiveStatusAdapter(
    private val userRepository: UserRepository,
) : UserChangeActiveStatusPort {

    @Transactional
    override fun changeActiveStatus(email: String) {
        val userEntity: UserEntity = userRepository.findByEmail(email)
            ?: throw EntityNotFoundException("user not found")
        userRepository.saveAndFlush(userEntity.changeActiveStatus())
    }
}

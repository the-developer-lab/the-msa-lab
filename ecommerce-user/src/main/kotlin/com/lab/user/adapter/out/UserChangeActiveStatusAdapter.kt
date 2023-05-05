package com.lab.user.adapter.out

import com.lab.user.adapter.out.persistence.user.UserEntity
import com.lab.user.adapter.out.persistence.user.UserRepository
import com.lab.user.application.port.out.UserChangeActiveStatusPort
import com.lab.user.global.annotation.Adapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.transaction.annotation.Transactional

@Adapter
class UserChangeActiveStatusAdapter(
    private val userRepository: UserRepository,
) : UserChangeActiveStatusPort {

    @Transactional
    override fun changeActiveStatus(email: String) {
        val userEntity: UserEntity = userRepository.findByEmail(email)
            ?: throw EntityNotFoundException()
        userRepository.saveAndFlush(userEntity.changeActiveStatus())
    }
}

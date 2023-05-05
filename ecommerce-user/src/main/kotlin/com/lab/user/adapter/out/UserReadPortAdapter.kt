package com.lab.user.adapter.out

import com.lab.user.adapter.out.persistence.user.UserRepository
import com.lab.user.application.port.out.UserReadPort
import com.lab.user.domain.user.User
import com.lab.user.global.annotation.Adapter
import jakarta.persistence.EntityNotFoundException

@Adapter
class UserReadPortAdapter(
    private val userRepository: UserRepository,
) : UserReadPort {

    override fun read(username: String): User =
        userRepository.findByUsername(username)
            ?.toDomainModel()
            ?: throw EntityNotFoundException()
}

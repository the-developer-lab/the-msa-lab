package com.lab.user.adapter.out.persistence.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByUsernameOrEmail(username: String, email: String): Boolean
    fun findByEmail(email: String): UserEntity?
}

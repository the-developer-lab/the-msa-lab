package com.lab.user.domain

data class User(
    val username: Username,
    val nickname: Nickname,
    val email: Email,
    val password: Password,
    val passwordExpireAt: PasswordExpireAt,
    val birthday: Birthday,
    val contactNumber: ContactNumber,
    val userStatus: UserStatus,
)

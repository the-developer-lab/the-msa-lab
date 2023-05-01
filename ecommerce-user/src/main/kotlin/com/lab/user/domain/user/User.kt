package com.lab.user.domain.user

import com.lab.user.domain.user.vo.Birthday
import com.lab.user.domain.user.vo.ContactNumber
import com.lab.user.domain.user.vo.Email
import com.lab.user.domain.user.vo.Nickname
import com.lab.user.domain.user.vo.Password
import com.lab.user.domain.user.vo.PasswordExpireAt
import com.lab.user.domain.user.vo.UserStatus
import com.lab.user.domain.user.vo.Username

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

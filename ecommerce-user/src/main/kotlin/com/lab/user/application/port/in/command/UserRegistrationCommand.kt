package com.lab.user.application.port.`in`.command

import com.lab.user.domain.Birthday
import com.lab.user.domain.ContactNumber
import com.lab.user.domain.Email
import com.lab.user.domain.Nickname
import com.lab.user.domain.Password
import com.lab.user.domain.PasswordExpireAt
import com.lab.user.domain.User
import com.lab.user.domain.UserStatus
import com.lab.user.domain.Username
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.ZoneId

data class UserRegistrationCommand(
    val username: String,
    val nickname: String,
    val email: String,
    val password: String,
    val passwordExpireAt: LocalDate = LocalDate.now(ZoneId.of("Asia/Seoul")).plusYears(3),
    val birthday: LocalDate,
    val contactNumber: String,
    val userStatus: String,
) {
    fun toUser(passwordEncoder: PasswordEncoder): User =
        User(
            Username(this.username),
            Nickname(this.nickname),
            Email(this.email),
            Password.encode(this.password, passwordEncoder),
            PasswordExpireAt(this.passwordExpireAt),
            Birthday(this.birthday),
            ContactNumber.createByDelimiter(this.contactNumber),
            UserStatus.NOT_AUTHORIZE
        )
}

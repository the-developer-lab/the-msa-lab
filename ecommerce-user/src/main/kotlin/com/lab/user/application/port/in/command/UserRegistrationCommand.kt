package com.lab.user.application.port.`in`.command

import com.lab.user.domain.user.User
import com.lab.user.domain.user.vo.Birthday
import com.lab.user.domain.user.vo.ContactNumber
import com.lab.user.domain.user.vo.Email
import com.lab.user.domain.user.vo.Nickname
import com.lab.user.domain.user.vo.Password
import com.lab.user.domain.user.vo.PasswordExpireAt
import com.lab.user.domain.user.vo.UserStatus
import com.lab.user.domain.user.vo.Username
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
    fun toDomainModel(passwordEncoder: PasswordEncoder): User =
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

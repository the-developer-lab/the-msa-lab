package com.lab.user.adapter.`in`.web.dto

import com.lab.user.application.port.`in`.command.UserRegistrationCommand
import com.lab.user.domain.user.vo.UserStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import java.time.ZoneId

class UserRegistrationRequest(
    @get:NotBlank
    var username: String,

    @get:NotBlank
    var nickname: String,

    @get:Email
    var email: String,

    @get:Length
    @get:NotBlank
    var password: String,

    @get:NotBlank
    var birthday: LocalDate,

    @get:NotBlank
    var contactNumber: String,
) {

    fun toCommand(): UserRegistrationCommand = UserRegistrationCommand(
        username = username,
        nickname = nickname,
        email = email,
        password = password,
        passwordExpireAt = LocalDate.now(ZoneId.of("Asia/Seoul")).plusYears(3),
        birthday = birthday,
        contactNumber = contactNumber,
        userStatus = UserStatus.NOT_AUTHORIZE.name
    )
}

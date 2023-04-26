package com.lab.user.adapter.out.persistence.user

import com.lab.user.domain.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "users")
@Entity
class UserEntity(

    var username: String,
    var nickname: String,
    var email: String,
    var password: String,
    var passwordExpireAt: LocalDate,
    var birthday: LocalDate,
    var contactNumber: String,
    var userStatus: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {
    constructor(user: User) : this(
        user.username.username,
        user.nickname.nickname,
        user.email.email,
        user.password.password,
        user.passwordExpireAt.passwordExpireAt,
        user.birthday.birthday,
        user.contactNumber.contactNumber,
        user.userStatus.name
    )
}

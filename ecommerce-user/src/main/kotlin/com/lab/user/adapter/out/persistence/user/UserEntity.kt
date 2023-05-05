package com.lab.user.adapter.out.persistence.user

import com.lab.user.domain.user.User
import com.lab.user.domain.user.vo.Birthday
import com.lab.user.domain.user.vo.ContactNumber
import com.lab.user.domain.user.vo.Email
import com.lab.user.domain.user.vo.Nickname
import com.lab.user.domain.user.vo.Password
import com.lab.user.domain.user.vo.PasswordExpireAt
import com.lab.user.domain.user.vo.UserStatus
import com.lab.user.domain.user.vo.Username
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
    fun changeActiveStatus(): UserEntity {
        this.userStatus = UserStatus.ACTIVE.name
        return this
    }

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

    fun toDomainModel(): User =
        User(
            username = Username(username),
            nickname = Nickname(nickname),
            email = Email(email),
            password = Password(password),
            passwordExpireAt = PasswordExpireAt(passwordExpireAt),
            birthday = Birthday(birthday),
            contactNumber = ContactNumber.createByDelimiter(contactNumber),
            userStatus = UserStatus.valueOf(userStatus)
        )
}

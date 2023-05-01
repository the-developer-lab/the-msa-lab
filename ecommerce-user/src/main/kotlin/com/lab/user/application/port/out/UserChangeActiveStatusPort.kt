package com.lab.user.application.port.out

fun interface UserChangeActiveStatusPort {
    fun changeActiveStatus(email: String)
}

package com.lab.user.global.error

class PasswordMisMatchException : RuntimeException() {
    override val message: String
        get() = "Input Invalid Password By Login"
}

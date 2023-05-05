package com.lab.user.global.error

class InvalidUserCreateAccessTokenException : RuntimeException() {
    override val message: String
        get() = "Input Invalid User Create AccessToken Request"
}

package com.lab.user.global.error

class InvalidUserSaveException : RuntimeException() {
    override val message: String
        get() = "Input Invalid User Save Request"
}

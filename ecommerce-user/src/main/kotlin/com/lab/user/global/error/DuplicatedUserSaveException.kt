package com.lab.user.global.error

class DuplicatedUserSaveException : RuntimeException() {
    override val message: String
        get() = "Input Duplicated User Save Request"
}

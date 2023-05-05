package com.lab.user.global.error

class DistributedLockTimeOutException : RuntimeException() {
    override val message: String
        get() = "DistributedLock Time Out"
}

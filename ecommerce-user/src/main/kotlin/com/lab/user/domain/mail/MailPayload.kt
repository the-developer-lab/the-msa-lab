package com.lab.user.domain.mail

data class MailPayload(
    val userId: Long,
    val email: String,
    val authenticationCode: String
) {
    val payload: String
        get() {
            return "{ \"userId\" : $userId, \"email\" : \"${email}\", \"authenticationCode\" : \"${authenticationCode}\" }"
        }
}

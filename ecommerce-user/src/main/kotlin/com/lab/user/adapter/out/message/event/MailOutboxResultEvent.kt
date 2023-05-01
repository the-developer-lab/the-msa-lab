package com.lab.user.adapter.out.message.event

import java.io.Serializable

data class MailOutboxResultEvent(
    var transactionId: String = "",
) : Serializable

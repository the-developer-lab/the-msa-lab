package com.lab.user.global.constant

const val X_USER_REGISTRATION = "x.user-registration"
const val X_POST_REGISTRATION = "x.post-registration"
const val Q_USER_REGISTRATION = "q.user-registration"
const val Q_POST_REGISTRATION_EMAIL = "q.post-registration-email"
const val Q_POST_REGISTRATION_EMAIL_PROCESSED = "q.post-registration-email-processed"
const val R_SEND_EMAIL = "send-email"

const val DLX_USER_REGISTRATION_FAILURE = "x.user-registration-failure"
const val DLX_POST_REGISTRATION_EMAIL_FAILURE = "x.post-registration-email-failure"
const val DLX_POST_REGISTRATION_EMAIL_PROCESSED_FAILURE = "x.post-registration-email-processed-failure"
const val DLQ_FALL_BACK_USER_REGISTRATION = "q.fall-back-user-registration"
const val DLQ_FALL_BACK_POST_REGISTRATION_EMAIL = "q.fall-back-post-registration-email"
const val DLQ_FALL_BACK_POST_REGISTRATION_EMAIL_PROCESSED = "q.fall-back-post-registration-email"
const val DLR_FALL_BACK = "fall-back"

const val ARG_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange"
const val ARG_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key"

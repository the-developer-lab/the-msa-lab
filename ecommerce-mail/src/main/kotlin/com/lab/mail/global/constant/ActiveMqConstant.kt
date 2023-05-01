package com.lab.mail.global.constant

// Subscribe
const val Q_POST_REGISTRATION_EMAIL = "q.post-registration-email"

// Publish
const val X_POST_REGISTRATION_PROCESSED = "x.post-registration-email-processed"
const val Q_POST_REGISTRATION_EMAIL_PROCESSED = "q.post-registration-email-processed"

// DeadLetter
const val DLX_POST_REGISTRATION_EMAIL_PROCESSED_FAILURE = "x.post-registration-email-processed-failure"
const val DLX_POST_REGISTRATION_EMAIL_FAILURE = "x.post-registration-email-failure"
const val DLQ_FALL_BACK_POST_REGISTRATION_EMAIL = "q.fall-back-post-registration-email"
const val DLR_FALL_BACK = "fall-back"

const val ARG_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange"
const val ARG_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key"

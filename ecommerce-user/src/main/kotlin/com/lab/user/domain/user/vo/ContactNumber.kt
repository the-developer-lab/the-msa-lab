package com.lab.user.domain.user.vo

data class ContactNumber(
    val firstNumber: String,
    val secondNumber: String,
    val thirdNumber: String,
    val deliMiter: String = "-",
) {

    val contactNumber: String
        get() = "${firstNumber}${deliMiter}${secondNumber}${deliMiter}$thirdNumber"

    companion object {
        private const val DEFAULT_DELIMITER = "-"

        fun createByDelimiter(contactNumber: String, deliMiter: String = DEFAULT_DELIMITER): ContactNumber {
            val contactNumberElements = contactNumber.split(deliMiter)
            return ContactNumber(
                contactNumberElements[0],
                contactNumberElements[1],
                contactNumberElements[2],
                deliMiter
            )
        }
    }
}

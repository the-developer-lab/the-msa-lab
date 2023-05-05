package com.lab.gateway.global.extension

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

fun Date.beforeNow(): Boolean {
    val today = Date.from(
        LocalDate.now()
            .atStartOfDay()
            .atZone(ZoneId.of("Asia/Seoul"))
            .toInstant()
    )
    return this.before(today)
}
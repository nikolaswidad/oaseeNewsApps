package com.nikolaswidad.oasenews.utils

import android.icu.util.TimeZone
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Utils {
    companion object {
        fun formatDateToId(
            currentDateString: String?,
            targetTimeZone: String = TimeZone.getDefault().id
        ): String {
            val instant = Instant.parse(currentDateString)
            val formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss")
                .withZone(ZoneId.of(targetTimeZone))
            return formatter.format(instant)
        }
    }
}
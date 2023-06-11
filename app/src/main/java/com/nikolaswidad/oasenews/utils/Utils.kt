package com.nikolaswidad.oasenews.utils

import android.icu.util.TimeZone
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

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

        fun parseDateLongToElapsedTime(value: String, shortOutput: Boolean = false): String {
            val timestamp = value.toLongOrNull()

            return if (timestamp != null) {
                val diff = Date().time - timestamp
                val time: String
                when {
                    // Minutes.
                    diff < 1000L * 60L * 60L -> {
                        val longEnding =
                            if ((diff / (1000L * 60L)) == 1L) " minute ago" else " minutes ago"
                        time = "${diff / (1000L * 60L)}" + if (shortOutput) "m" else longEnding
                    }
                    // Hours.
                    diff < 1000L * 60L * 60L * 24L -> {
                        val longEnding =
                            if ((diff / (1000L * 60L * 60L)) == 1L) " hour ago" else " hours ago"
                        time = "${diff / (1000L * 60L * 60L)}" + if (shortOutput) "h" else longEnding
                    }
                    // Days.
                    diff < 1000L * 60L * 60L * 24L * 30L -> {
                        val longEnding =
                            if ((diff / (1000L * 60L * 60L * 24L)) == 1L) " day ago" else " days ago"
                        time = "${diff / (1000L * 60L * 60L * 24L)}" + if (shortOutput) "d" else longEnding
                    }
                    // Months.
                    diff < 1000L * 60L * 60L * 24L * 30L * 12L -> {
                        val longEnding =
                            if ((diff / (1000L * 60L * 60L * 24L * 30L)) == 1L) " month ago" else " months ago"
                        time =
                            "${diff / (1000L * 60L * 60L * 24L * 30L)}" + if (shortOutput) "M" else longEnding
                    }
                    // Years.
                    else -> {
                        val longEnding =
                            if ((diff / (1000L * 60L * 60L * 24L * 30L * 12L)) == 1L) " year ago" else " years ago"
                        time =
                            "${diff / (1000L * 60L * 60L * 24L * 30L * 12L)}" + if (shortOutput) "Y" else longEnding
                    }
                }
                time
            } else {
                // Handle the case when the value cannot be converted to a Long.
                // Return an appropriate default value or error message.
                ""
            }
        }


        // must Long PubDate
//        fun parseDateLongToElapsedTime(value: Long, shortOutput: Boolean = false) : String {
//
//            val diff = Date().time - value
//            val time: String
//
//            when {
//                // Minutes.
//                diff < 1000L * 60L * 60L -> {
//                    val longEnding =
//                        if ((diff / (1000L * 60L)) == 1L) " minute ago" else " minutes ago"
//                    time = "${diff / (1000L * 60L)}" + if (shortOutput) "m" else longEnding
//                }
//                // Hours.
//                diff < 1000L * 60L * 60L * 24L -> {
//                    val longEnding =
//                        if ((diff / (1000L * 60L * 60L)) == 1L) " hour ago" else " hours ago"
//                    time = "${diff / (1000L * 60L * 60L)}" + if (shortOutput) "h" else longEnding
//                }
//                // Days.
//                diff < 1000L * 60L * 60L * 24L * 30L -> {
//                    val longEnding =
//                        if ((diff / (1000L * 60L * 60L * 24L)) == 1L) " day ago" else " days ago"
//                    time = "${diff / (1000L * 60L * 60L * 24L)}" + if (shortOutput) "d" else longEnding
//                }
//                // Months.
//                diff < 1000L * 60L * 60L * 24L * 30L * 12L -> {
//                    val longEnding =
//                        if ((diff / (1000L * 60L * 60L * 24L * 30L)) == 1L) " month ago" else " months ago"
//                    time =
//                        "${diff / (1000L * 60L * 60L * 24L * 30L)}" + if (shortOutput) "M" else longEnding
//                }
//                // Years.
//                else -> {
//                    val longEnding =
//                        if ((diff / (1000L * 60L * 60L * 24L * 30L * 12L)) == 1L) " year ago" else " years ago"
//                    time =
//                        "${diff / (1000L * 60L * 60L * 24L * 30L * 12L)}" + if (shortOutput) "Y" else longEnding
//                }
//            }
//
//            return time
//        }
    }
}
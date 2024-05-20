package com.example.healthcare.helper

import java.text.SimpleDateFormat
import java.util.Locale

class DateUtils {
    companion object {
        fun parseDateToIndonesianFormat(dateUnformatted: String) : String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val locale = Locale("id", "ID")
            val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", locale)
            val date = inputFormat.parse(dateUnformatted)

            return outputFormat.format(date!!)
        }
    }
}
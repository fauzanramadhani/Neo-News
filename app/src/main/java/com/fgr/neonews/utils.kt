package com.fgr.neonews

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.truncate(maxLength: Int): String {
    return if (length > maxLength) {
        substring(0, maxLength) + "..."
    } else {
        this
    }
}

fun String.isoToMills(): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")

    try {
        val date = dateFormat.parse(this)
        return date?.time ?: 0
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return 0
}

fun Long.formatMills(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(Date(this))
}

object NewsCategories {
    const val Local = "Lokal"
    const val Abroad = "Mancanegara"
    const val PalestineWar = "Perang Palestina"
    const val Sport = "Olahraga"
}

sealed class UiState<out T : Any?> {

    object Empty : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}
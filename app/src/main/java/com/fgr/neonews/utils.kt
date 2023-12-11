package com.fgr.neonews

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.truncate(maxLength: Int): String {
    return if (length > maxLength) {
        substring(0, maxLength) + "..."
    } else {
        this
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimestamp(
    localDate: LocalDate,
    pattern: String
): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return localDate.format(formatter)
}

object NewsCategories {
    const val Local = "Lokal"
    const val Abroad = "Mancanegara"
    const val PalestineWar = "Perang Palestina"
    const val Sport = "Olahraga"
}

sealed class UiState<out T: Any?> {

    object Empty : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}
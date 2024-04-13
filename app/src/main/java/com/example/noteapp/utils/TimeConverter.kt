package com.example.noteapp.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private var formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss", Locale.getDefault())

fun formatTime(time: Long): String {
    return formatter.format(Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()))
}
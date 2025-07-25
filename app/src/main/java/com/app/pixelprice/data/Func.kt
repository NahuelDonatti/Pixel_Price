package com.app.pixelprice.data

import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatReleaseDate(timestampSeconds: Long?): String? {
    if (timestampSeconds == null || timestampSeconds == 0L) return null
    return try {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        sdf.format(Date(timestampSeconds * 1000))
    } catch (e: Exception) {
        null
    }
}

fun String.toFirestoreSafeId(): String { return URLEncoder.encode(this, StandardCharsets.UTF_8.toString()) }
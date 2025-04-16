package com.example.fetch.utils

fun extractItemNumber(name: String?): Int {
    return name?.let { Regex("""\d+""").find(it)?.value?.toIntOrNull() } ?: Int.MAX_VALUE
}
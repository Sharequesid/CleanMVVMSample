package com.example.cleanmvvmsample.model


data class Pokemon(
    val entryNumber: Long,
    val name: String,
    val url: String,
    var isFavorite: Boolean = false
)

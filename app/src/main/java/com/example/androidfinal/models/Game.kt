package com.example.androidfinal.models

import java.util.Date

data class Game(
    val id: String = "",
    val name: String ="",
    val release_date: String="",
    val genres: List<String>? = null,
    val rating: Double = 0.0,
    val pictures: List<String>? = null,
    val info: String = "",
    val cost: Double = 0.0,
    val blocked: Boolean = false
)

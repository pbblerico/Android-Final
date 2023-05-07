package com.example.androidfinal.models

import java.util.Date

data class Game(
    val name: String,
    val release_date: Date,
    val genres: List<Genre>,
    val rating: Double,
    val info: String = "",
    val blocked: Boolean = false
)

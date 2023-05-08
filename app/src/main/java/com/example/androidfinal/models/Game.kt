package com.example.androidfinal.models

import java.util.Date

data class Game(
    val id: String,
    val name: String,
    val release_date: Date,
    val genres: List<Genre>,
    val rating: Double,
    val pictures: List<String>,
    val info: String = "",
    val cost: Double = 0.0,
    val blocked: Boolean = false
)

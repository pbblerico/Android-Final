package com.example.androidfinal.models

import java.util.*

data class Comment (
    val id: String = "",
    val author_id: String = "",
    var text: String = "",
    var game_id: String = "",
    val date_created: String = "",
    val date_updated: String = ""
)
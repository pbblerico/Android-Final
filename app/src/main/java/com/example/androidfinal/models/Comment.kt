package com.example.androidfinal.models

import java.util.*

data class Comment (
    val id: String,
    val author_id: String,
    var text: String,
    val date_created: Date,
    val date_updated: Date
)
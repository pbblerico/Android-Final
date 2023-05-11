package com.example.androidfinal.models

import java.util.*

data class Notification (
    val id: String,
    val author_id: String,
    val publisher_id: String,
    var text: String,
    val date_created: Date,
)
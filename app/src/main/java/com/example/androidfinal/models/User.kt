package com.example.androidfinal.models

import com.example.androidfinal.enums.Role

data class User(
    var id: String ="",
    val username: String,
    val email: String,
    val first_name: String = "",
    val last_name: String = "",
    val blocked: Boolean = false,
    var role: Role = Role.CLIENT
    )

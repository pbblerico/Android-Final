package com.example.androidfinal.models

import com.example.androidfinal.enums.Role

data class User(
    var id: String ="",
    var username: String = "",
    var email: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var blocked: Boolean = false,
    var role: Role = Role.CLIENT
    )

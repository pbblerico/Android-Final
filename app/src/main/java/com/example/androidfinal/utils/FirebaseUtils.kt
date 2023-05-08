package com.example.androidfinal.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val ref = FirebaseDatabase.getInstance()

}
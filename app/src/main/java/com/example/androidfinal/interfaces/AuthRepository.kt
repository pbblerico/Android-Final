package com.example.androidfinal.interfaces

import com.example.androidfinal.models.User
import com.example.androidfinal.utils.Result
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<AuthResult>

    suspend fun signUp(email: String, password: String, username: String): Result<AuthResult>
    fun logout()
}
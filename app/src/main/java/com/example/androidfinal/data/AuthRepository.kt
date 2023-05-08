package com.example.androidfinal.data

import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<AuthResult>

    suspend fun signUp(email: String, password: String, username: String): Result<AuthResult>
}
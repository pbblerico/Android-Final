package com.example.androidfinal.data

import com.example.androidfinal.models.User
import com.example.androidfinal.utils.FirebaseUtils
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(): AuthRepository {

    override suspend fun signUp(email: String, password: String, username: String): Result<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                val result = FirebaseUtils.auth.createUserWithEmailAndPassword(email, password).await()

                val uid = FirebaseUtils.auth.uid!!
                val user = User(id = uid, email = email, username = username)

                FirebaseUtils.ref.getReference("Users").child(uid).setValue(user).await()
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e.message ?: "An unknown Error Occurred")
            }
        }
    }

    override suspend fun login(email: String, password: String): Result<AuthResult> {
        return withContext(Dispatchers.IO) {
           try {
               val result = FirebaseUtils.auth.signInWithEmailAndPassword(email, password).await()
               Result.Success(result)
            } catch (e: java.lang.Exception) {
               Result.Error(e.message ?: "An unknown Error Occurred")
            }
        }
    }

    fun logout() {}
}
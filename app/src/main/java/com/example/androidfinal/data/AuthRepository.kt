package com.example.androidfinal.data

import com.example.androidfinal.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val ref = FirebaseDatabase.getInstance().getReference("Users")

    suspend fun signUp(email: String, password: String, username: String): Result<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()

                val uid = auth.uid!!
                val user = User(id = uid, email = email, username = username)

                ref.child(uid).setValue(user).await()
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e.message ?: "An unknown Error Occurred")
            }
        }
    }

    suspend fun login(email: String, password: String): Result<AuthResult> {
        return withContext(Dispatchers.IO) {
           try {
               val result = auth.signInWithEmailAndPassword(email, password).await()
               Result.Success(result)
            } catch (e: java.lang.Exception) {
               Result.Error(e.message ?: "An unknown Error Occurred")
            }
        }
    }

    fun logout() {}
//    val currentUser: FirebaseUser?
//    suspend fun login(email: String, password: String): Resourse<FirebaseUser>
//    suspend fun signup(username: String, email: String, password: String): Resourse<FirebaseUser>
//    fun logout()
}
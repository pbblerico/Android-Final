package com.example.androidfinal.repositories

import android.util.Log
import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Genre
import com.example.androidfinal.models.Publisher
import com.example.androidfinal.models.User
import com.example.androidfinal.repositories.interfaces.UserRepository
import com.example.androidfinal.utils.FirebaseUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.androidfinal.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class UserRepositoryImpl: UserRepository {
    override suspend fun getUserList(result: (Result<List<User>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<User>()
            FirebaseUtils.ref.getReference("Users")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val user = ds.getValue(User::class.java)

                            list.add(user!!)
                        }
                        result.invoke(
                            Result.Success(list)
                        )
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(
                            Result.Error("cancelled")
                        )
                    }

                })
        }
    }


    override suspend fun getUserInfo(user_id: String, result: (Result<User>) -> Unit) {
        withContext(Dispatchers.IO) {
            FirebaseUtils.ref.getReference("Users").child(user_id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        result.invoke(
                            Result.Success(user!!)
                        )
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(
                            Result.Error("cancelled")
                        )
                    }

                })
        }
    }

    override suspend fun addGameToCart(game_id: String, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {

            FirebaseUtils.ref.getReference("Users").child("6RqKBh0cvDW99UXQ1IqJ5zh8fIo2")
                .child("Cart").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val id = "${ds.value}"
                            if(id == game_id) {
                                result.invoke(
                                    Result.Error("game already in the cart")
                                )
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

            val timestamp = System.currentTimeMillis().toString()
//            FirebaseUtils.ref.getReference("Users").child("6RqKBh0cvDW99UXQ1IqJ5zh8fIo2")
//                .child("Cart").child(timestamp).setValue(game_id)
//                .addOnSuccessListener {
//                    result.invoke(
//                        Result.Success("Game added to your cart")
//                    )
//                }
//                .addOnFailureListener {e ->
//                    e.localizedMessage
//                }
        }
    }

    override suspend fun getUserGameList(user_id: String): Result<List<Game>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPublisherList(result: (Result<List<Publisher>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Publisher>()
            FirebaseUtils.ref.getReference("Publisher")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val publisher = ds.getValue(Publisher::class.java)

                            list.add(publisher!!)
                        }
                        result.invoke(
                            Result.Success(list)
                        )
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(
                            Result.Error("cancelled")
                        )
                    }

                })
        }
    }


}
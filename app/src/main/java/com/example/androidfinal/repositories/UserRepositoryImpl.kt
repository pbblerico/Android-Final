package com.example.androidfinal.repositories

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

class UserRepositoryImpl(): UserRepository {
    override suspend fun getGameList(result: (Result<List<Game>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Game>()
            FirebaseUtils.ref.getReference("Game")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val game = ds.getValue(Game::class.java)
//                            val id = "${ds}"

                            list.add(game!!)
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

    override suspend fun getGameInfo(): Result<Game> {
        TODO("Not yet implemented")
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
                    }

                })
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

    override suspend fun getGenreList(result: (Result<List<Genre>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Genre>()
            FirebaseUtils.ref.getReference("Genre")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val genre = ds.getValue(Genre::class.java)

                            list.add(genre!!)
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
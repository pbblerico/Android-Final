package com.example.androidfinal.repositories

import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Genre
import com.example.androidfinal.models.User
import com.example.androidfinal.repositories.interfaces.GameRepository
import com.example.androidfinal.utils.FirebaseUtils
import com.example.androidfinal.utils.Result
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepositoryImpl: GameRepository {
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

    override suspend fun getGameInfo(game_id: String, result: (Result<Game>) -> Unit) {
        withContext(Dispatchers.IO) {
            FirebaseUtils.ref.getReference("Game").child(game_id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val game = snapshot.getValue(Game::class.java)
                        result.invoke(
                            Result.Success(game!!)
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


    override suspend fun addGame(game: Game, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
//            val timestamp = System.currentTimeMillis().toString()
//            val newGenre = Genre(id = timestamp, name = genre)
            FirebaseUtils.ref.getReference("Game")
                .child(game.id).setValue(game)
                .addOnSuccessListener {
                    result.invoke(
                        Result.Success("Successfully created")
                    )
                }
                .addOnFailureListener {e ->
                    e.localizedMessage
                }
        }
    }

    override suspend fun updateGame(game: HashMap<String, Any>, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            FirebaseUtils.ref.getReference("Game")
                .child(game["id"].toString()).updateChildren(game)
                .addOnSuccessListener {
                    result.invoke(
                        Result.Success("Successfully updated")
                    )
                }
                .addOnFailureListener {e ->
                    e.localizedMessage
                }
        }
    }

    override suspend fun deleteGame(game_id: String, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            FirebaseUtils.ref.getReference("Game")
                .child(game_id).removeValue()
                .addOnSuccessListener {
                    result.invoke(
                        Result.Success("Successfully deleted")
                    )
                }
                .addOnFailureListener {e ->
                    e.localizedMessage
                }
        }
    }

    override suspend fun blockGame(game_id: String, result: (Result<String>) -> Unit) {
        TODO("Not yet implemented")
    }
}
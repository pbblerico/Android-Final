package com.example.androidfinal.repositories

import com.example.androidfinal.models.Comment
import com.example.androidfinal.models.Genre
import com.example.androidfinal.repositories.interfaces.CommentsRepository
import com.example.androidfinal.utils.FirebaseUtils
import com.example.androidfinal.utils.Result
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentsRepositoryImpl: CommentsRepository {
    override suspend fun getCommentList(result: (Result<List<Comment>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Comment>()
            FirebaseUtils.ref.getReference("Comments")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val comment = ds.getValue(Comment::class.java)

                            list.add(comment!!)
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

    override suspend fun addComment(comment: Comment, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            val timestamp = System.currentTimeMillis().toString()
            FirebaseUtils.ref.getReference("Genre")
                .child(timestamp).setValue(comment)
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

    override suspend fun getGameComments(game_id: String, result: (Result<List<Comment>>) -> Unit) {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<Comment>()
            FirebaseUtils.ref.getReference("Comments")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(ds in snapshot.children) {
                            val comment = ds.getValue(Comment::class.java)

                            if(comment!!.game_id == game_id) {
                                list.add(comment!!)
                            }
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
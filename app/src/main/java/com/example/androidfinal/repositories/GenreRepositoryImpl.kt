package com.example.androidfinal.repositories

import com.example.androidfinal.models.Genre
import com.example.androidfinal.repositories.interfaces.GenreRepository
import com.example.androidfinal.utils.FirebaseUtils
import com.example.androidfinal.utils.Result
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenreRepositoryImpl: GenreRepository {
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

    override suspend fun addGenre(genre: String, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            val timestamp = System.currentTimeMillis().toString()
            val newGenre = Genre(id = timestamp, name = genre)
            FirebaseUtils.ref.getReference("Genre")
                .child(timestamp).setValue(newGenre)
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

    override suspend fun updateGenre(genre: HashMap<String, Any>, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            FirebaseUtils.ref.getReference("Genre")
                .child(genre["id"].toString()).updateChildren(genre)
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

    override suspend fun deleteGenre(genre_id: String, result: (Result<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            FirebaseUtils.ref.getReference("Genre")
                .child(genre_id).removeValue()
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
}
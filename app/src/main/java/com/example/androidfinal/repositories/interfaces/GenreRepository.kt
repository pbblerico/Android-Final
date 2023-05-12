package com.example.androidfinal.repositories.interfaces

import com.example.androidfinal.models.Genre
import com.example.androidfinal.utils.Result

interface GenreRepository {
    suspend fun getGenreList(result: (Result<List<Genre>>) -> Unit)

    suspend fun addGenre(genre: String, result: (Result<String>) -> Unit)

    suspend fun updateGenre(genre: HashMap<String, Any>, result: (Result<String>) -> Unit)

    suspend fun deleteGenre(genre: String, result: (Result<String>) -> Unit)
}
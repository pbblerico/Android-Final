package com.example.androidfinal.repositories.interfaces

import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Genre
import com.example.androidfinal.models.Publisher
import com.example.androidfinal.models.User
import com.example.androidfinal.utils.Result

interface UserRepository {

    suspend fun getGameList(result: (Result<List<Game>>) -> Unit)

    suspend fun getGameInfo(): Result<Game>

    suspend fun getUserInfo(user_id: String, result: (Result<User>) -> Unit)

    suspend fun getUserGameList(user_id: String): Result<List<Game>>

    suspend fun getPublisherList(result: (Result<List<Publisher>>) -> Unit)

    suspend fun getGenreList(result: (Result<List<Genre>>) -> Unit)
}
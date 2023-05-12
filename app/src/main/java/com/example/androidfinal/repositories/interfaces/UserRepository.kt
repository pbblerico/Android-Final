package com.example.androidfinal.repositories.interfaces

import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Genre
import com.example.androidfinal.models.Publisher
import com.example.androidfinal.models.User
import com.example.androidfinal.utils.Result

interface UserRepository {

    suspend fun getUserList(result: (Result<List<User>>) -> Unit)
    suspend fun getUserInfo(user_id: String, result: (Result<User>) -> Unit)

    suspend fun addGameToCart(game_id: String, result: (Result<String>) -> Unit)

    suspend fun getUserGameList(user_id: String): Result<List<Game>>

    suspend fun getPublisherList(result: (Result<List<Publisher>>) -> Unit)


}
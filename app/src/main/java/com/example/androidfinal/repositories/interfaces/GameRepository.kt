package com.example.androidfinal.repositories.interfaces

import com.example.androidfinal.models.Game
import com.example.androidfinal.utils.Result

interface GameRepository {

    suspend fun getGameList(result: (Result<List<Game>>) -> Unit)

    suspend fun getGameInfo(game_id: String, result: (Result<Game>) -> Unit)

    suspend fun addGame(game: Game, result: (Result<String>) -> Unit)

    suspend fun updateGame(game: HashMap<String, Any>, result: (Result<String>) -> Unit)

    suspend fun deleteGame(game_id: String, result: (Result<String>) -> Unit)

    suspend fun blockGame(game_id: String, result: (Result<String>) -> Unit)

}
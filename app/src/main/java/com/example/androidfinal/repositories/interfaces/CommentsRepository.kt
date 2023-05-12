package com.example.androidfinal.repositories.interfaces

import com.example.androidfinal.models.Comment
import com.example.androidfinal.models.Genre
import com.example.androidfinal.utils.Result

interface CommentsRepository {
    suspend fun getCommentList(result: (Result<List<Comment>>) -> Unit)

    suspend fun addComment(comment: Comment, result: (Result<String>) -> Unit)

    suspend fun getGameComments(game_id: String, result: (Result<List<Comment>>) -> Unit)

}
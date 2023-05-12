package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.models.Comment
import com.example.androidfinal.models.Game
import com.example.androidfinal.repositories.CommentsRepositoryImpl
import com.example.androidfinal.repositories.GameRepositoryImpl
import com.example.androidfinal.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(
    private val repository: CommentsRepositoryImpl = CommentsRepositoryImpl(),
    private val repGame: GameRepositoryImpl = GameRepositoryImpl()
): ViewModel() {
    private var _game = MutableLiveData<Result<Game>>()
    val game: LiveData<Result<Game>> = _game

    private var _comments = MutableLiveData<Result<List<Comment>>>()
    val comments: LiveData<Result<List<Comment>>> = _comments


    private var _addCom = MutableLiveData<Result<String>>()
    val addCom: LiveData<Result<String>> = _addCom

    fun getComments(game_id: String) {
        this._comments.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getGameComments(game_id) {
                _comments.value = it
            }
        }
    }

    fun getGameInfo(game_id: String) {
        this._game.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repGame.getGameInfo(game_id) {
                _game.value = it
            }
        }
    }

    fun addComment(game_id: String, text: String) {
        this._addCom.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            val timestamp = System.currentTimeMillis().toString()
            val comment = Comment(timestamp, "kmirova@gmail.com", text, game_id, "2023-05-12", "2023-05-12")
            repository.addComment(comment) {
                _addCom.value = it
            }
        }
    }
}
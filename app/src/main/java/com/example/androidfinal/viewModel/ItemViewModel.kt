package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.models.Comment
import com.example.androidfinal.models.Game
import com.example.androidfinal.repositories.CommentsRepositoryImpl
import com.example.androidfinal.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(
    private val repository: CommentsRepositoryImpl = CommentsRepositoryImpl()
): ViewModel() {
    private var _game = MutableLiveData<Result<Game>>()
    val game: LiveData<Result<Game>> = _game

    private var _comments = MutableLiveData<Result<List<Comment>>>()
    val comments: LiveData<Result<List<Comment>>> = _comments

    fun getComments(game_id: String) {
        this._comments.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getGameComments(game_id) {
                _comments.value = it
            }
        }
    }
}
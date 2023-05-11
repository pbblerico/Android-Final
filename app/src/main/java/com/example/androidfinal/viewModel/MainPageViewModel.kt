package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Genre
import com.example.androidfinal.models.Publisher
import com.example.androidfinal.repositories.UserRepositoryImpl
import com.example.androidfinal.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPageViewModel(private val repository: UserRepositoryImpl = UserRepositoryImpl()): ViewModel() {
    private var _games = MutableLiveData<Result<List<Game>>>()
    val games: LiveData<Result<List<Game>>>
        get() = _games


    private var _publishers = MutableLiveData<Result<List<Publisher>>>()
    val publishers: LiveData<Result<List<Publisher>>>
        get() = _publishers

    private var _genres = MutableLiveData<Result<List<Genre>>>()
    val genres: LiveData<Result<List<Genre>>>
        get() = _genres


    fun getGamesList() {
        this._games.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getGameList {
                _games.value = it
            }
        }
    }

    fun getPublishersList() {
        this._publishers.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getPublisherList {
                _publishers.value = it
            }
        }
    }

    fun getGenreList() {
        this._genres.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getGenreList {
                _genres.value = it
            }
        }
    }
}
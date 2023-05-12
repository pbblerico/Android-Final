package com.example.androidfinal.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Genre
import com.example.androidfinal.models.Publisher
import com.example.androidfinal.models.User
import com.example.androidfinal.repositories.GameRepositoryImpl
import com.example.androidfinal.repositories.GenreRepositoryImpl
import com.example.androidfinal.repositories.UserRepositoryImpl
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.session.currentSession
import com.example.androidfinal.utils.FirebaseUtils
import com.example.androidfinal.utils.Result
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val repository: UserRepositoryImpl = UserRepositoryImpl(),
    private val gameRep: GameRepositoryImpl = GameRepositoryImpl()
): ViewModel() {
    private var _games = MutableLiveData<Result<List<Game>>>()
    val games: LiveData<Result<List<Game>>>
        get() = _games


    private var _publishers = MutableLiveData<Result<List<Publisher>>>()
    val publishers: LiveData<Result<List<Publisher>>>
        get() = _publishers

    private var _users = MutableLiveData<Result<List<User>>>()
    val users: LiveData<Result<List<User>>>
        get() = _users

    private var _genres = MutableLiveData<Result<List<Genre>>>()
    val genres: LiveData<Result<List<Genre>>>
        get() = _genres

    private var _addGenre = MutableLiveData<Result<String>>()
    val addGenre: LiveData<Result<String>>
        get() = _addGenre

    private var _deleteGenre = MutableLiveData<Result<String>>()
    val deleteGenre: LiveData<Result<String>>
        get() = _deleteGenre

    private var _addGame = MutableLiveData<Result<String>>()
    val addGame: LiveData<Result<String>>
        get() = _addGame

    private val _user = MutableLiveData<Result<User>>()
    val user: LiveData<Result<User>> = _user

    fun getUserInfo() {
        _user.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main){
            repository.getUserInfo(FirebaseUtils.auth.uid!!) {
                _user.value = it
            }
        }
    }


    fun getGamesList() {
        this._games.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            gameRep.getGameList {
                _games.value = it
            }
        }
    }

    fun addGameToCart(game_id: String) {
        this._addGame.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.addGameToCart(game_id) {
                _addGame.value = it
            }
        }
    }

//    fun getPublishersList() {
//        this._publishers.value = Result.Loading()
//        viewModelScope.launch(Dispatchers.Main) {
//            repository.getPublisherList {
//                _publishers.value = it
//            }
//        }
//    }

    fun getUserList() {
        this._users.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getUserList {
                _users.value = it
            }
        }
    }

//    fun addGenre(genre_name: String) {
//        viewModelScope.launch(Dispatchers.Main) {
//            currep.addGenre(genre_name) {
//                _addGenre.value = it
//            }
//        }
//    }
//
//    fun deleteGenre(genre_id: String) {
//        viewModelScope.launch(Dispatchers.Main) {
//            currep.deleteGenre("1683824637952") {
//                _deleteGenre.value = it
//            }
//        }
//    }

//    fun getGenreList() {
//        this._genres.value = Result.Loading()
//        viewModelScope.launch(Dispatchers.Main) {
//            repository.getGenreList {
//                _genres.value = it
//            }
//        }
//    }
}
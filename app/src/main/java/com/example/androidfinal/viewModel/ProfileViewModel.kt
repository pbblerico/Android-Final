package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.models.User
import com.example.androidfinal.repositories.UserRepositoryImpl
import com.example.androidfinal.repositories.interfaces.UserRepository
import com.example.androidfinal.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepositoryImpl = UserRepositoryImpl()): ViewModel() {
    private var _user = MutableLiveData<Result<User>>()
    val user: LiveData<Result<User>>
        get() = _user

    fun getUserInfo(user_id: String) {
        this._user.value = Result.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getUserInfo(user_id) {
                _user.value = it
            }
        }
    }
}
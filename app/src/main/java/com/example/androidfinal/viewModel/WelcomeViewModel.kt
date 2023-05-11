package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidfinal.repositories.AuthRepositoryImpl
import com.example.androidfinal.utils.Result
import com.google.firebase.auth.AuthResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: AuthRepositoryImpl = AuthRepositoryImpl()): ViewModel() {
    private val _loggedInStatus = MutableLiveData<Result<AuthResult>>()
    val loggedInStatus: LiveData<Result<AuthResult>> = _loggedInStatus

    fun login(email: String, password: String) {
        _loggedInStatus.postValue(Result.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            _loggedInStatus.postValue(repository.login(email, password))
        }
    }
}
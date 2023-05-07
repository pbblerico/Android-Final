package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.data.AuthRepository
import com.google.firebase.auth.AuthResult
import com.example.androidfinal.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _userLoginStatus = MutableLiveData<Result<AuthResult>>()
    val userLoginStatus: LiveData<Result<AuthResult>> = _userLoginStatus

    fun login(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _userLoginStatus.postValue(Result.Error("Blank fields"))
        } else {
            _userLoginStatus.postValue(Result.Loading())
            viewModelScope.launch(Dispatchers.Main){
                val loginResult = authRepository.login(email, password)
                _userLoginStatus.postValue(loginResult)
            }
        }
    }
}
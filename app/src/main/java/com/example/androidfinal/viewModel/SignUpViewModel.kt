package com.example.androidfinal.viewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.data.AuthRepositoryImpl
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.androidfinal.data.Result

class SignUpViewModel(private val authRepositoryImpl: AuthRepositoryImpl = AuthRepositoryImpl()): ViewModel() {
    private val _userSignUpStatus = MutableLiveData<Result<AuthResult>>()
    val userSignUpStatus: LiveData<Result<AuthResult>> = _userSignUpStatus

    fun signUp(username: String, email: String, password1: String, password2: String) {
        val error =
            if (username.isEmpty() || email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                "Empty Strings"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                "Not a valid Email"
            } else if(password1 != password2) {
                "Wrong Password Repeated"
            }
            else null

        error?.let {
            _userSignUpStatus.postValue(Result.Error(it))
            return
        }
        _userSignUpStatus.postValue(Result.Loading())

        viewModelScope.launch(Dispatchers.Main) {
            val registerResult = authRepositoryImpl.signUp(username = username, email = email, password = password1)
            _userSignUpStatus.postValue(registerResult)
        }
    }
}
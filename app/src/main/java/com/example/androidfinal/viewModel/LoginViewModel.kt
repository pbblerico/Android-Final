package com.example.androidfinal.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.models.User
import com.example.androidfinal.repositories.AuthRepositoryImpl
import com.example.androidfinal.repositories.UserRepositoryImpl
import com.example.androidfinal.repositories.interfaces.UserRepository
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.session.currentSession
import com.example.androidfinal.utils.FirebaseUtils
import com.google.firebase.auth.AuthResult
import com.example.androidfinal.utils.Result
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl = AuthRepositoryImpl(),
    private val userRepository: UserRepositoryImpl = UserRepositoryImpl()
): ViewModel() {
    private val _userLoginStatus = MutableLiveData<Result<AuthResult>>()
    val userLoginStatus: LiveData<Result<AuthResult>> = _userLoginStatus

    private val _user = MutableLiveData<Result<User>>()
    val user: LiveData<Result<User>> = _user

    fun login(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _userLoginStatus.postValue(Result.Error("Blank fields"))
        } else {
            _userLoginStatus.postValue(Result.Loading())
            viewModelScope.launch(Dispatchers.Main){
                val loginResult = authRepositoryImpl.login(email, password)
                _userLoginStatus.postValue(loginResult)
            }
        }
    }

    fun createLoginSession(cont: Context, email: String, password: String) {
        val session = LoginPrefs(cont)
        val id = FirebaseUtils.auth.uid!!
        var role = "CLIENT"
        FirebaseUtils.ref.getReference("Users").child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                role = "${snapshot.child("role").value}"
            }

            override fun onCancelled(error: DatabaseError) {}

        })
        session.createLoginSession(email, password, id, role)
    }


}
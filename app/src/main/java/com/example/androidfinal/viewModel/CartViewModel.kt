package com.example.androidfinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfinal.models.Game
import com.example.androidfinal.utils.Result

class CartViewModel(): ViewModel() {
    private var _games = MutableLiveData<Result<List<Game>>>()
    val games: LiveData<Result<List<Game>>>
        get() = _games


}
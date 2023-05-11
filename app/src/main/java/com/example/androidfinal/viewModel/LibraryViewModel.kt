package com.example.androidfinal.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfinal.models.Game
import com.example.androidfinal.utils.Result

class LibraryViewModel(): ViewModel() {
    private var _games = MutableLiveData<Result<List<Game>>>()
}
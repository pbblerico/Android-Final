package com.example.androidfinal.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfinal.models.Publisher
import com.example.androidfinal.utils.Result

class СompanyViewModel(): ViewModel() {
    private var _companies = MutableLiveData<Result<List<Publisher>>>()
}
package com.example.androidfinal.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfinal.models.Game
import com.example.androidfinal.models.Notification
import com.example.androidfinal.utils.Result

class NotificationsViewModel(): ViewModel() {
    private var _notifications = MutableLiveData<Result<List<Notification>>>()
}
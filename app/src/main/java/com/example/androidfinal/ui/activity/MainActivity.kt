package com.example.androidfinal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidfinal.R
import com.example.androidfinal.databinding.ActivityMainBinding
import com.example.androidfinal.enums.Role

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MAIN", Role.CLIENT.toString())
    }
    fun setBottomNavBar() {
        binding.botNavBar.inflateMenu(R.menu.user_menu)

    }
}
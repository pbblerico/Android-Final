package com.example.androidfinal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.androidfinal.R
import com.example.androidfinal.databinding.ActivityMainBinding
import com.example.androidfinal.session.LoginPrefs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var session: LoginPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideProgressBar()
        this.session = LoginPrefs(this)
    }

    fun hideProgressBar() {
        binding.prgBar.visibility = View.GONE
    }

    fun showProgressBar() {
        binding.prgBar.visibility = View.VISIBLE
    }
    fun setBottomNavBar() {
        binding.botNavBar.menu.clear()
        val menu = when(this.session.getUserRole()) {
            "ADMIN" -> R.menu.admin_menu
            "SELLER" -> R.menu.seller_menu
            else -> R.menu.user_menu
        }
        binding.botNavBar.inflateMenu(menu)
//        AppBarConfiguration(setOf())

        val nav = findNavController(R.id.fragment)
        binding.botNavBar.setupWithNavController(nav)
    }

//    fun setMenu(menu: Int) {
//        binding.topbar.menu.clear()
//        binding.topbar.inflateMenu(menu)
//    }

//    override fun onClick(view: View?) {
//        when(view) {
//            binding.
//        }
//    }
}
package com.example.androidfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentWelcomeBinding
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.ui.activity.MainActivity
import com.example.androidfinal.utils.Result
import com.example.androidfinal.viewModel.WelcomeViewModel


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

        val session = LoginPrefs(requireContext())
//        session.logoutUser()
        if(session.isLoggedIn()) {
            login()
        }



        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toLoginFragment)
        }

        binding.button2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toSignupFragment)
        }
        return binding.root
    }


    fun login() {
        viewModel.login("kmirova@gmail.com", "123456")


        viewModel.loggedInStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    (activity as MainActivity).setBottomNavBar()
                    Navigation.findNavController(requireView()).navigate(R.id.toMainPage)
                }
                is Result.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
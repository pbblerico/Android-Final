package com.example.androidfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentLoginBinding
import com.example.androidfinal.viewModel.LoginViewModel
import com.example.androidfinal.data.Result

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginBtn.setOnClickListener {
            viewModel.login("kmirova@gmail.com", "123456")

        }

        viewModel.userLoginStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    Navigation.findNavController(requireView()).navigate(R.id.toMainPageFragment)
                }
                is Result.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
//        binding.button2.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.toSignupFragment)
//        }
//        binding.button.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.toMainPageFragment)
//        }
        return binding.root
    }
}
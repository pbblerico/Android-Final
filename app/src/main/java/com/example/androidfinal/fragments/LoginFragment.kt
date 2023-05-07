package com.example.androidfinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

//        binding.button2.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.toSignupFragment)
//        }
//        binding.button.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.toMainPageFragment)
//        }
        return binding.root
    }
}
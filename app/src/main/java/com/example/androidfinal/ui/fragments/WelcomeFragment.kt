package com.example.androidfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toLoginFragment)
        }

        binding.button2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toSignupFragment)
        }
        return binding.root
    }
}
package com.example.androidfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentItemBinding

class ItemFragment : Fragment(R.layout.fragment_item) {
    private lateinit var binding: FragmentItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)

        return binding.root
    }
}
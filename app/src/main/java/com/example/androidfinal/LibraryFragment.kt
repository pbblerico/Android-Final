package com.example.androidfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidfinal.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment(R.layout.fragment_library) {
    private lateinit var binding: FragmentLibraryBinding
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)

       return binding.root
    }
}
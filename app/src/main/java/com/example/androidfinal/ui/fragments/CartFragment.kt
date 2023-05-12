package com.example.androidfinal.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentCartBinding
import com.example.androidfinal.viewModel.CartViewModel
import com.example.androidfinal.viewModel.MainPageViewModel

class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
  @SuppressLint("SuspiciousIndentation")
  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.backToMainPage)
            }
        })

        return binding.root
    }
}
package com.example.androidfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.ui.activity.MainActivity
import com.example.androidfinal.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private lateinit var binding: FragmentMainPageBinding
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
//        view

       val curUser = "CLIENT"
       if(curUser == "CLIENT") {
            binding.text.visibility = View.GONE
       }
       (activity as MainActivity).setBottomNavBar()
       binding.button3.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.toLoginFragment)
       }

//       val callback = object : OnBackPressedCallback(
//           true
//       ) {
//           override fun handleOnBackPressed() {
//               Navigation.findNavController(view!!).navigate(R.id.action_mainPageFragment_to_loginFragment)
//           }
//       }

       //manages
       requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
           override fun handleOnBackPressed() {
//               Navigation.findNavController(view!!).navigate(R.id.action_mainPageFragment_to_loginFragment)
//               Log.d("TAG", "here")
               requireActivity().finish()
           }
       })
//       requireActivity().onBackPressedDispatcher.onBackPressed()

       binding.button4.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.toItemFragment)
       }
       return binding.root
    }

}
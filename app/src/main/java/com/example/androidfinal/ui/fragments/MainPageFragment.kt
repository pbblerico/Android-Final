package com.example.androidfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.ui.activity.MainActivity
import com.example.androidfinal.databinding.FragmentMainPageBinding
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.utils.Result
import com.example.androidfinal.viewModel.LoginViewModel
import com.example.androidfinal.viewModel.MainPageViewModel

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewModel: MainPageViewModel
    private lateinit var act: MainActivity

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainPageViewModel::class.java]
        act = activity as MainActivity


       observer()
//       val curUser = "CLIENT"
//       if(curUser == "CLIENT") {
//            binding.text.visibility = View.GONE
//       }
       binding.button3.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.toLoginFragment)
       }

       requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
           override fun handleOnBackPressed() {
               requireActivity().finish()
           }
       })

       binding.button4.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.toItemFragment)
       }
       return binding.root
    }
    private fun observer(){
        viewModel.getGamesList()
        viewModel.games.observe(viewLifecycleOwner) { state ->
            when(state){
                is Result.Loading -> {
                    Log.d("TAG", "Loading")
                    act.showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                }
                is Result.Success -> {
                    state.data?.forEach { it ->
                        Log.d("TAG", it.toString())
                    }
                    act.hideProgressBar()

                }
            }
        }

        viewModel.getPublishersList()
        viewModel.publishers.observe(viewLifecycleOwner) { state ->
            when(state){
                is Result.Loading -> {
                    Log.d("TAG", "Loading")
                    act.showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                }
                is Result.Success -> {
                    state.data?.forEach { it ->
                        Log.d("TAG", it.toString())
                    }
                    act.hideProgressBar()

                }
            }
        }

        viewModel.getGenreList()
        viewModel.genres.observe(viewLifecycleOwner) { state ->
            when(state){
                is Result.Loading -> {
                    Log.d("TAG", "Loading")
                    act.showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                }
                is Result.Success -> {
                    state.data?.forEach { it ->
                        Log.d("TAG", it.toString())
                    }
                    act.hideProgressBar()

                }
            }
        }

    }


}
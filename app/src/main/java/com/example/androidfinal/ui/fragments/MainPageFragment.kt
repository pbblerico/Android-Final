package com.example.androidfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.androidfinal.adapters.GameListAdapter
import com.example.androidfinal.ui.activity.MainActivity
import com.example.androidfinal.databinding.FragmentMainPageBinding
import com.example.androidfinal.models.Game
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.utils.Result
import com.example.androidfinal.viewModel.LoginViewModel
import com.example.androidfinal.viewModel.MainPageViewModel

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewModel: MainPageViewModel
    private lateinit var act: MainActivity
    private lateinit var cont: Context

    private lateinit var adapterGameList: GameListAdapter

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainPageViewModel::class.java]
        act = activity as MainActivity


       adapterGameList = GameListAdapter(viewModel)
       binding.rlGames.adapter = adapterGameList

       binding.bookET.addTextChangedListener (object : TextWatcher {
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
               try {
                   adapterGameList.filter.filter(s)
               }
               catch (e: Exception) {

               }
           }

           override fun afterTextChanged(p0: Editable?) {
           }

       } )

       observer()


//       val curUser = "CLIENT"
//       if(curUser == "CLIENT") {
//            binding.text.visibility = View.GONE
//       }


       requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
           override fun handleOnBackPressed() {
               requireActivity().finish()
           }
       })

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
                    Log.d("ga", "workd")
                    adapterGameList.updateList(state.data!!)
                    act.hideProgressBar()

                }
            }
        }
        viewModel.addGame.observe(viewLifecycleOwner) { state ->
            when(state){
                is Result.Loading -> {
                    Log.d("TAG", "Loading")
                    act.showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                }
                is Result.Success -> {
                    act.hideProgressBar()
                    Log.d("view", "game added to cart")

                }
            }
        }




    }


}
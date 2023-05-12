package com.example.androidfinal.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.androidfinal.R
import com.example.androidfinal.adapters.CommentsAdapter
import com.example.androidfinal.databinding.FragmentItemBinding
import com.example.androidfinal.ui.activity.MainActivity
import com.example.androidfinal.utils.Result
import com.example.androidfinal.viewModel.ItemViewModel

class ItemFragment : Fragment(R.layout.fragment_item) {
    private lateinit var binding: FragmentItemBinding
    val args: ItemFragmentArgs by navArgs()
    private lateinit var viewModel: ItemViewModel
    private lateinit var adapterComments: CommentsAdapter
    private lateinit var act: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        val id = args.id
        act = activity as MainActivity



        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        adapterComments = CommentsAdapter(viewModel)
        binding.comments.adapter = adapterComments

        getComments(id)

        return binding.root
    }

    private fun getComments(game_id: String) {
        viewModel.getComments(game_id)
        viewModel.comments.observe(viewLifecycleOwner) {state ->
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
                    adapterComments.updateList(state.data!!)
                    state.data!!.forEach {
                        Log.d("view", it.text)
                    }


                }
            }
        }
    }
}
package com.example.androidfinal.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.example.androidfinal.R
import com.example.androidfinal.adapters.CommentsAdapter
import com.example.androidfinal.databinding.FragmentItemBinding
import com.example.androidfinal.models.Game
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

        binding.add.setOnClickListener {
            addComment(id, binding.addComm.text.toString())
        }

        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        adapterComments = CommentsAdapter(viewModel)
        binding.comments.adapter = adapterComments

        getGame(id)

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

    private fun getGame(game_id: String) {
        viewModel.getGameInfo(game_id)
        viewModel.game.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Result.Loading -> {
                    Log.d("TAG", "Loading")
                    act.showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                    act.hideProgressBar()
                }
                is Result.Success -> {
                    act.hideProgressBar()
                    setGame(state.data!!)
                }
            }
        }
    }

    private fun addComment(game_id: String, text: String) {
        viewModel.addComment(game_id, text)
        viewModel.addCom.observe(viewLifecycleOwner) {
            state ->
            when (state) {
                is Result.Loading -> {
                    Log.d("TAG", "Loading")
                    act.showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                    act.hideProgressBar()
                }
                is Result.Success -> {
                    act.hideProgressBar()
                }
            }

        }
    }

    private fun setGame(game: Game) {
        binding.ImgGame.load(game.pictures!![0]) {
            crossfade(true)
            placeholder(R.drawable.poster_placeholder)
            scale(Scale.FILL)
        }
        binding.gameName.text = game.name
        binding.cost.text = game.cost.toString()
        binding.rating.text = game.rating.toString()
        binding.description.text = game.info
    }
}
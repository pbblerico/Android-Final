package com.example.androidfinal.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.androidfinal.R
import com.example.androidfinal.databinding.ItemViewBinding
import com.example.androidfinal.filters.FilterGame
import com.example.androidfinal.models.Game
import com.example.androidfinal.ui.fragments.MainPageFragmentDirections
import com.example.androidfinal.viewModel.MainPageViewModel
import com.google.android.material.imageview.ShapeableImageView

class GameListAdapter: RecyclerView.Adapter<GameListAdapter.HolderGame>, Filterable {
    private lateinit var binding: ItemViewBinding
    var gameList: List<Game> = arrayListOf()
    private var filterList = gameList
//
    private var filter: FilterGame? = null
    private val viewModel: MainPageViewModel

    constructor(viewModel: MainPageViewModel) {
        this.viewModel = viewModel

    }

    inner class HolderGame(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img: ShapeableImageView = binding.ImgGame
        var name: TextView = binding.gameName
        var cost: TextView = binding.cost
        var publisher: TextView = binding.company
        var rating: TextView = binding.rating
        var cart: Button = binding.toCart

        @SuppressLint("SetTextI18n")
        fun bind(item: Game) {
            binding.apply {
                name.text = item.name
                cost.text = item.cost.toString()
                rating.text = item.rating.toString()
                ImgGame.load(item.pictures!![0]) {
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                    scale(Scale.FILL)
                }
            }
        }
    }

    fun updateList(list: List<Game>) {
        this.gameList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderGame {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderGame(binding.root)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: HolderGame, position: Int) {
        val model = gameList[position]

        holder.bind(gameList[position])

        holder.cart.setOnClickListener {
            viewModel.addGameToCart(model.id)
        }

        holder.itemView.setOnClickListener {
            val action = MainPageFragmentDirections.toItemFragment(model.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getFilter(): Filter {
        if(filter == null) {
            filter = FilterGame(filterList, this)
        }
        return filter as FilterGame
    }
}
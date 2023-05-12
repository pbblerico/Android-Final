package com.example.androidfinal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.androidfinal.R
import com.example.androidfinal.databinding.ItemViewBinding
import com.example.androidfinal.models.Game
import com.google.android.material.imageview.ShapeableImageView

class PublisherGameListAdapter : RecyclerView.Adapter<PublisherGameListAdapter.HolderGame> {
    private lateinit var binding: ItemViewBinding
    private var context: Context
    var gameList: List<Game>
//    private var filterList: ArrayList<Book>
//
//    private var filter: FilterBook? = null

    constructor(context: Context, gameList: List<Game>) {
        this.context = context
        this.gameList = gameList
//        this.filterList = bookArrayList
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

//    override fun getFilter(): Filter {
//        if(filter == null) {
//            filter = FilterBook(filterList, this)
//        }
//        return filter as FilterBook
//    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderGame {
        binding = ItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderGame(binding.root)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: HolderGame, position: Int) {
        val model = gameList[position]

        holder.bind(gameList[position])

        holder.itemView.setOnClickListener {
//            val intent = Intent(context, BookDetailActivity::class.java)
//            intent.putExtra("bookId", id)
//            context.startActivity(intent)
        }
    }
}
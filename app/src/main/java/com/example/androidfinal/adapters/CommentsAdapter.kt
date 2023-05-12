package com.example.androidfinal.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.androidfinal.R
import com.example.androidfinal.databinding.CommentViewBinding
import com.example.androidfinal.databinding.ItemViewBinding
import com.example.androidfinal.filters.FilterGame
import com.example.androidfinal.models.Comment
import com.example.androidfinal.models.Game
import com.example.androidfinal.ui.fragments.MainPageFragmentDirections
import com.example.androidfinal.viewModel.ItemViewModel
import com.example.androidfinal.viewModel.MainPageViewModel
import com.google.android.material.imageview.ShapeableImageView

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.HolderComment> {
    private lateinit var binding: CommentViewBinding
    var commentList: List<Comment> = arrayListOf()
//
    private val viewModel: ItemViewModel

    constructor(viewModel: ItemViewModel) {
        this.viewModel = viewModel

    }

    inner class HolderComment(itemView: View): RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Comment) {
            binding.apply {
                    auth.text = item.author_id
                    comment.text = item.text
                    date.text = item.date_created.toString()
                }
            }
        }


    fun updateList(list: List<Comment>) {
        this.commentList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderComment {
        binding = CommentViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderComment(binding.root)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: HolderComment, position: Int) {
        val model = commentList[position]

        holder.bind(commentList[position])


        holder.itemView.setOnClickListener {
//            val action = MainPageFragmentDirections.toItemFragment(model.game_id)
//            Navigation.findNavController(it).navigate(action)
//            val intent = Intent(context, BookDetailActivity::class.java)
//            intent.putExtra("bookId", id)
//            context.startActivity(intent)
        }
    }
}
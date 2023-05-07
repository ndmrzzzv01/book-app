package com.books.app.screens.main.views.holders

import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.Book
import com.books.app.databinding.ItemBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ItemBookViewHolder(private val binding: ItemBookBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book?) {
        Glide
            .with(binding.imgPoster)
            .load(book?.coverUrl)
            .into(binding.imgPoster)
        binding.tvName.text = book?.name
    }

    val mainLayout = binding.imageBackground
}
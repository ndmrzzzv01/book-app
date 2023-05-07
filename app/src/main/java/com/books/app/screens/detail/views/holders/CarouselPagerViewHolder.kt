package com.books.app.screens.detail.views.holders

import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.Book
import com.books.app.databinding.ItemCarouselBinding
import com.bumptech.glide.Glide

class CarouselPagerViewHolder(private val binding: ItemCarouselBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book?) {
        Glide.with(binding.imgPoster).load(book?.coverUrl).into(binding.imgPoster)
    }

}
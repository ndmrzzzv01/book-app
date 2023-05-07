package com.books.app.screens.detail.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.BannerInfo
import com.books.app.data.Book
import com.books.app.databinding.ItemBannerBinding
import com.books.app.databinding.ItemCarouselBinding
import com.books.app.screens.detail.views.holders.CarouselPagerViewHolder
import com.books.app.screens.main.views.holders.SliderBannerViewHolder

class CarouselPagerAdapter(private var listOfBooks: List<Book?>) :
    RecyclerView.Adapter<CarouselPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselPagerViewHolder {
        val view =
            ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselPagerViewHolder, position: Int) {
        val book = listOfBooks[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = listOfBooks.size

    fun updateList(listOfBooks: List<Book?>) {
        this.listOfBooks = listOfBooks
        notifyDataSetChanged()
    }
}


package com.books.app.screens.main.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.app.OnBookItemClick
import com.books.app.data.Book
import com.books.app.databinding.ItemBookBinding
import com.books.app.screens.main.views.holders.ItemBookViewHolder

class ItemBookAdapter(
    private var listOfBooks: List<Book?>,
    private val onBookItemClick: OnBookItemClick
) : RecyclerView.Adapter<ItemBookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBookViewHolder {
        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemBookViewHolder, position: Int) {
        val book = listOfBooks[position]
        holder.mainLayout.setOnClickListener {
            onBookItemClick.onBookItemClick(book?.genre ?: "", position)
        }
        holder.bind(book)
    }

    override fun getItemCount(): Int = listOfBooks.size

    fun updateListOfBooks(books: List<Book?>) {
        listOfBooks = books
        notifyDataSetChanged()
    }
}
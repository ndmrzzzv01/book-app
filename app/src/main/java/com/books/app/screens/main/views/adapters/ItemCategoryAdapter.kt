package com.books.app.screens.main.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.Book
import com.books.app.databinding.ItemCategoryBinding
import com.books.app.screens.main.views.holders.ItemCategoryViewHolder

class ItemCategoryAdapter(private var hashOfTitlesAndBooks: MutableMap<String, List<Book?>>) :
    RecyclerView.Adapter<ItemCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCategoryViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemCategoryViewHolder, position: Int) {
        val keyByPosition = hashOfTitlesAndBooks.keys.toList()[position]
        val valueByKey = hashOfTitlesAndBooks.getValue(keyByPosition)
        holder.bind(Pair(keyByPosition, valueByKey))
    }

    override fun getItemCount(): Int = hashOfTitlesAndBooks.size

    fun updateHashOfTitlesAndBooks(hash: MutableMap<String, List<Book?>>) {
        hashOfTitlesAndBooks = hash
        notifyDataSetChanged()
    }
}
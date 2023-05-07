package com.books.app.screens.main.views.holders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.Book
import com.books.app.databinding.ItemCategoryBinding
import com.books.app.screens.main.views.adapters.ItemBookAdapter

class ItemCategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val adapterForCategories = ItemBookAdapter(listOf())

    fun bind(pairOfTitleAndBooks: Pair<String, List<Book?>>) {
        binding.tvCategories.text = pairOfTitleAndBooks.first

        adapterForCategories.updateListOfBooks(pairOfTitleAndBooks.second)
        binding.rvInnerCategories.adapter = adapterForCategories
        binding.rvInnerCategories.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
    }
}
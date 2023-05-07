package com.books.app.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.Book
import com.books.app.databinding.FragmentMainBinding
import com.books.app.screens.main.views.adapters.ItemCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    private val adapter = ItemCategoryAdapter(hashMapOf())
    private val hashOfTitleAndBooks = mutableMapOf<String, List<Book?>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllBooksFromFirebase()
        viewModel.listOfBooks.observe(viewLifecycleOwner) { listOfBooks ->
            for (book in listOfBooks) {
                val listOfBooksByGenre = listOfBooks.filter { it?.genre == book?.genre }
                hashOfTitleAndBooks[book?.genre ?: ""] = listOfBooksByGenre
            }
            adapter.updateHashOfTitlesAndBooks(hashOfTitleAndBooks)
            binding.rvCategories.adapter = adapter
            binding.rvCategories.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

}
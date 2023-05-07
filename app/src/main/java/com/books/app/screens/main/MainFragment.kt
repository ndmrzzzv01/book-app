package com.books.app.screens.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.books.app.OnBookItemClick
import com.books.app.data.Book
import com.books.app.databinding.FragmentMainBinding
import com.books.app.screens.main.views.adapters.ItemCategoryAdapter
import com.books.app.screens.main.views.adapters.SliderBannerPagerAdapter
import com.books.app.views.autoScroll
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), OnBookItemClick {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private val adapter = ItemCategoryAdapter(hashMapOf())
    private val bannerAdapter = SliderBannerPagerAdapter(listOf())

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
        viewModel.getTopBannerSliders()
        viewModel.getAllBooksFromFirebase()
        initObservers()
    }

    private fun initObservers() {
        viewModel.listOfBooks.observe(viewLifecycleOwner) { listOfBooks ->
            for (book in listOfBooks) {
                val listOfBooksByGenre = listOfBooks.filter { it?.genre == book?.genre }
                hashOfTitleAndBooks[book?.genre ?: ""] = listOfBooksByGenre
            }
            adapter.updateHashOfTitlesAndBooks(hashOfTitleAndBooks)
            binding.rvCategories.adapter = adapter
            binding.rvCategories.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        viewModel.listOfTopBannerSliders.observe(viewLifecycleOwner) { listOfBannersInfo ->
            bannerAdapter.updateList(listOfBannersInfo)
            binding.pager.adapter = bannerAdapter
            binding.pager.autoScroll(3000, listOfBannersInfo.size)
            TabLayoutMediator(binding.tablayout, binding.pager) { _, _ -> }.attach()
        }
    }

    override fun onClick(id: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(id)
        )
    }

}
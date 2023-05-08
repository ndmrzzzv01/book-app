package com.books.app.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.books.app.OnBookItemClick
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

    private val viewModel by activityViewModels<MainViewModel>()

    private val adapter = ItemCategoryAdapter(hashMapOf(), this)
    private val bannerAdapter = SliderBannerPagerAdapter(listOf(), this)

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
        viewModel.listOfTopBannerSliders.observe(viewLifecycleOwner) { listOfBannersInfo ->
            bannerAdapter.updateList(listOfBannersInfo)
            binding.pager.adapter = bannerAdapter
            binding.pager.autoScroll(4000, listOfBannersInfo.size)
            TabLayoutMediator(binding.tablayout, binding.pager) { _, _ -> }.attach()
        }

        viewModel.mapOfBooks.observe(viewLifecycleOwner) { mapOfBooks ->
            adapter.updateHashOfTitlesAndBooks(mapOfBooks)
            binding.rvCategories.adapter = adapter
            binding.rvCategories.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override fun onBookItemClick(key: String, index: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(key, index)
        )
    }

}
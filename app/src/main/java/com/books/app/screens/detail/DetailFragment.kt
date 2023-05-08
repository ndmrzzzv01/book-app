package com.books.app.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.books.app.data.Book
import com.books.app.databinding.FragmentDetailBinding
import com.books.app.screens.detail.views.adapters.CarouselPagerAdapter
import com.books.app.screens.main.MainViewModel
import com.books.app.views.adjustViewPager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        const val SLIDERS_VALUES_KEY = "slider"
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val adapter = CarouselPagerAdapter(listOf())

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val detailViewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.pager.adjustViewPager(requireContext())
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                detailViewModel.setCurrentPosition(position)
            }
        })

    }

    private fun initObservers() {
        val mapOfBooks = mainViewModel.mapOfBooks.value
        val listOfTopBannersSliders = mainViewModel.listOfTopBannerSliders.value
        val key = args.key
        val values = if (key == SLIDERS_VALUES_KEY) {
            // TODO
            listOf<Book>()
        } else {
            mapOfBooks?.get(args.key)
        }
        detailViewModel.setList(values)
        adapter.updateList(values ?: listOf())
        binding.pager.adapter = adapter

        binding.pager.post {
            binding.pager.currentItem = args.index
        }
    }
}
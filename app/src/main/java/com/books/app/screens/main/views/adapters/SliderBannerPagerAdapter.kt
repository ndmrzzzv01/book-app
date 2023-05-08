package com.books.app.screens.main.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.app.OnBookItemClick
import com.books.app.data.BannerInfo
import com.books.app.databinding.ItemBannerBinding
import com.books.app.screens.detail.DetailFragment
import com.books.app.screens.main.views.holders.SliderBannerViewHolder
import com.bumptech.glide.Glide

class SliderBannerPagerAdapter(
    private var listOfTopBannerSliders: List<BannerInfo?>,
    private val onBookItemClick: OnBookItemClick
) : RecyclerView.Adapter<SliderBannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderBannerViewHolder {
        val view =
            ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderBannerViewHolder, position: Int) {
        val slider = listOfTopBannerSliders[position]
        holder.mainLayout.setOnClickListener {
            onBookItemClick.onClick(DetailFragment.SLIDERS_VALUES_KEY, slider?.bookId ?: 0)
        }
        holder.bind(slider)
    }

    override fun getItemCount(): Int = listOfTopBannerSliders.size

    fun updateList(listOfBannerSliders: List<BannerInfo?>) {
        listOfTopBannerSliders = listOfBannerSliders
        notifyDataSetChanged()
    }

}


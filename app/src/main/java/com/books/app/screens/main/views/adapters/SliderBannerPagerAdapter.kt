package com.books.app.screens.main.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.BannerInfo
import com.books.app.databinding.ItemBannerBinding
import com.bumptech.glide.Glide

class SliderBannerPagerAdapter(private var listOfTopBannerSliders: List<BannerInfo?>) :
    RecyclerView.Adapter<SliderBannerPagerAdapter.SliderBannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderBannerViewHolder {
        val view =
            ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderBannerViewHolder, position: Int) {
        val slider = listOfTopBannerSliders[position]
        holder.bind(slider)
    }

    override fun getItemCount(): Int = listOfTopBannerSliders.size

    fun updateList(listOfBannerSliders: List<BannerInfo?>) {
        listOfTopBannerSliders = listOfBannerSliders
        notifyDataSetChanged()
    }

    inner class SliderBannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sliderItem: BannerInfo?) {
            Glide.with(binding.imageBanner).load(sliderItem?.cover).into(binding.imageBanner)
        }
    }
}


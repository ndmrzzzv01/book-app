package com.books.app.screens.main.views.holders

import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.BannerInfo
import com.books.app.databinding.ItemBannerBinding
import com.bumptech.glide.Glide

class SliderBannerViewHolder(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(sliderItem: BannerInfo?) {
        Glide.with(binding.imageBanner).load(sliderItem?.cover).into(binding.imageBanner)
    }

    val mainLayout = binding.imageBackground

}
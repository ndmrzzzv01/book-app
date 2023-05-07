package com.books.app.data

import com.google.gson.annotations.SerializedName

data class BannerInfo(
    val id: Int? = null,
    @SerializedName("book_id") val bookId: Int? = null,
    val cover: String? = null
)

data class TopBannerSlidesResponse(
    @SerializedName("top_banner_slides") val result: List<BannerInfo>
)

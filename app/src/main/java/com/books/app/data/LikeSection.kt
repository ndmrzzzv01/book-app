package com.books.app.data

import com.google.gson.annotations.SerializedName

data class LikeSection(
    @SerializedName("you_will_like_section") val result: List<Int>
)
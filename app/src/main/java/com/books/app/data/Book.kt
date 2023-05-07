package com.books.app.data

import com.google.gson.annotations.SerializedName

data class Book(
    val id: Int? = null,
    val name: String? = null,
    val author: String? = null,
    val summary: String? = null,
    val genre: String? = null,
    @SerializedName("cover_url") val coverUrl: String? = null,
    val views: String? = null,
    val likes: String? = null,
    val quotes: String? = null
)

data class BooksResponse(
    val books: List<Book>
)
package com.books.app.repositories

import com.books.app.data.BannerInfo
import com.books.app.data.Book
import com.books.app.data.BooksResponse
import com.books.app.data.TopBannerSlidesResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ViewModelScoped
class BooksRepository @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) {

    suspend fun getAllBooks(): List<Book> {
        val listOfBooks = mutableListOf<Book>()
        val gson = Gson()
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val books =
                    gson.fromJson(remoteConfig.getString("json_data"), BooksResponse::class.java)
                listOfBooks.addAll(books.books)
            }

        }.await()
        return listOfBooks
    }

    suspend fun getBannersInfo(): List<BannerInfo> {
        val listOfBannersTopSlides = mutableListOf<BannerInfo>()
        val gson = Gson()
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val topBannerSlides =
                    gson.fromJson(
                        remoteConfig.getString("json_data"),
                        TopBannerSlidesResponse::class.java
                    )
                listOfBannersTopSlides.addAll(topBannerSlides.result)
            }

        }.await()
        return listOfBannersTopSlides
    }
}
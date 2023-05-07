package com.books.app.repositories

import com.books.app.data.Book
import com.books.app.data.BooksResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ViewModelScoped
class BooksRepository @Inject constructor() {

    // переписать на корутину
    suspend fun getAllBooks(): List<Book> {
        val listOfBooks = mutableListOf<Book>()
        val gson = Gson()
        val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val defaultConfigs = mapOf(
            "json_data" to "books"
        )
        remoteConfig.setDefaultsAsync(defaultConfigs)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val books =
                    gson.fromJson(remoteConfig.getString("json_data"), BooksResponse::class.java)
                listOfBooks.addAll(books.books)
            }

        }.await()
        return listOfBooks
    }

}
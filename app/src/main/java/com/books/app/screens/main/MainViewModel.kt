package com.books.app.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.BannerInfo
import com.books.app.data.Book
import com.books.app.repositories.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _mutableMapOfBooks = MutableLiveData<Map<String, List<Book>>>()
    val mapOfBooks: LiveData<Map<String, List<Book>>> = _mutableMapOfBooks

    private val _listOfTopBannerSliders = MutableLiveData<List<BannerInfo?>>()
    val listOfTopBannerSliders: LiveData<List<BannerInfo?>> = _listOfTopBannerSliders

    fun getAllBooksFromFirebase() {
        viewModelScope.launch {
            val hashOfTitleAndBooks = mutableMapOf<String, List<Book>>()
            val listOfBooks = booksRepository.getAllBooks()
            for (book in listOfBooks) {
                val listOfBooksByGenre = listOfBooks.filter { it.genre == book.genre }
                hashOfTitleAndBooks[book.genre ?: ""] = listOfBooksByGenre
            }
            _mutableMapOfBooks.value = hashOfTitleAndBooks
        }
    }

    fun getTopBannerSliders() {
        viewModelScope.launch {
            _listOfTopBannerSliders.value = booksRepository.getBannersInfo()
        }
    }
}
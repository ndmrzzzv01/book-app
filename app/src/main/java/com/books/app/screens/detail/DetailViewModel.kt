package com.books.app.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.books.app.data.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _listOfBooks = MutableLiveData<List<Book>?>()

    private val _currentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book> = _currentBook

    fun setList(newListOfBooks: List<Book>?) {
        _listOfBooks.value = newListOfBooks
    }

    fun setCurrentPosition(position: Int) {
        _currentBook.value = _listOfBooks.value?.get(position)
    }

}
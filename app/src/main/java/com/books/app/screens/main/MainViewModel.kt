package com.books.app.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.Book
import com.books.app.repositories.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val booksRepository: BooksRepository
): ViewModel() {

    private val _listOfBooks = MutableLiveData<List<Book?>>()
    val listOfBooks: LiveData<List<Book?>> = _listOfBooks

    fun getAllBooksFromFirebase() {
        viewModelScope.launch {
           _listOfBooks.value = booksRepository.getAllBooks()
        }
    }
}
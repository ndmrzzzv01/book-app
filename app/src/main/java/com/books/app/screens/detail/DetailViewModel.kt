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

    fun getResultListByKey(key: String, index: Int, mapOfBooks: Map<String, List<Book>>?) =
        if (key == DetailFragment.SLIDERS_VALUES_KEY) {
            val result = mutableListOf<Book>()
            val values = mapOfBooks?.values
            values?.forEach {
                for (book in it) {
                    if (book.id == index) {
                        mapOfBooks[book.genre]?.toList()
                            ?.let { listOfBooks -> result.addAll(listOfBooks) }
                    }
                }
            }
            result
        } else {
            mapOfBooks?.get(key)
        }

    fun setList(newListOfBooks: List<Book>?) {
        _listOfBooks.value = newListOfBooks
    }

    fun setCurrentPosition(position: Int) {
        _currentBook.value = _listOfBooks.value?.get(position)
    }

}
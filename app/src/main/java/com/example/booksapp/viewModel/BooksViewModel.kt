package com.example.booksapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.model.Book
import com.example.booksapp.model.BooksResponse
import com.example.booksapp.repository.BookRepository
import com.example.booksapp.util.Resource
import kotlinx.coroutines.launch

class BooksViewModel(private val bookRepository: BookRepository) : ViewModel(){

    val books : MutableLiveData<Resource<BooksResponse>> = MutableLiveData()

    fun getAllBooks() = viewModelScope.launch {
        books.postValue(Resource.Loading())
        val response = bookRepository.getBooks()
    }
}
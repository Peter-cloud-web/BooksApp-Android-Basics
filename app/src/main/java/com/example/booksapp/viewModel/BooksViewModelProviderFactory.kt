package com.example.booksapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksapp.repository.BookRepository

class BooksViewModelProviderFactory( val bookRepository: BookRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BooksViewModel(bookRepository) as T
    }

}
package com.example.booksapp.repository

import com.example.booksapp.api.NetworkCall

class BookRepository {

    suspend fun getBooks() = NetworkCall.provideMovies.getBooks();
}
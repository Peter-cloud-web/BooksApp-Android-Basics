package com.example.booksapp.api

import com.example.booksapp.model.Book
import com.example.booksapp.model.BooksResponse
import retrofit2.Call
import retrofit2.http.GET

interface BooksApi {
    @GET("1.0/new")
    fun getBooks(): Call<BooksResponse>
}
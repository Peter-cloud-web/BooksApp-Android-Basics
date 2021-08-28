package com.example.booksapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.itbook.store"
object NetworkCall {

    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    val provideMovies:BooksApi by lazy {
        provideRetrofitInstance().create(BooksApi::class.java)
    }
}
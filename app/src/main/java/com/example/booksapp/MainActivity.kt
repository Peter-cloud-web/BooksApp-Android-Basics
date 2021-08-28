package com.example.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.adapter.BooksAdapter
import com.example.booksapp.api.NetworkCall
import com.example.booksapp.model.Book
import com.example.booksapp.model.BooksResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.setOnRefreshListener {
            fetchMovies()
        }

        fetchMovies()
    }

    private fun fetchMovies(){
        swipeRefresh.isRefreshing = true

        NetworkCall.provideMovies.getBooks().enqueue(object : Callback<BooksResponse> {
            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                swipeRefresh.isRefreshing = false
                val books = response.body()

                books?.let {
                    showBooks(it)
                }
            }

        })

        }

    private fun showBooks(book: BooksResponse) {
        recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        recyclerViewBooks.adapter = BooksAdapter(book)
    }
}


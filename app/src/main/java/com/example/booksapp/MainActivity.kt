package com.example.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.adapter.BooksAdapter
import com.example.booksapp.api.NetworkCall
import com.example.booksapp.model.BooksResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        swipeRefresh.setOnRefreshListener {
            fetchBooks()
        }
        fetchBooks()
    }

    private fun fetchBooks(){
        swipeRefresh.isRefreshing = true

        CoroutineScope(Dispatchers.Main).launch {

            try{

                val response = NetworkCall.provideMovies.getBooks()
                if(response.isSuccessful && response.body() != null){
                  swipeRefresh.isRefreshing = false
                  val data = response.body()
                  data?.let{
                      showBooks(it)
                  }
                }else{

                }

            } catch(e:Exception){
                Toast.makeText(this@MainActivity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }



        }

    private fun showBooks(book: BooksResponse) {
        recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        recyclerViewBooks.adapter = BooksAdapter(book,this)
    }
}


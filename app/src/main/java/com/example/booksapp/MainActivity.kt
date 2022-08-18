package com.example.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.adapter.BooksAdapter
import com.example.booksapp.api.NetworkCall
import com.example.booksapp.model.BooksResponse
import com.example.booksapp.repository.BookRepository
import com.example.booksapp.util.Resource
import com.example.booksapp.viewModel.BooksViewModel
import com.example.booksapp.viewModel.BooksViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val bookRepository = BookRepository()
    val viewModelFactory = BooksViewModelProviderFactory(bookRepository)
    booksViewModel = ViewModelProvider(this,viewModelFactory).get(BooksViewModel::class.java)

        swipeRefresh.setOnRefreshListener {
            fetchBooks()
        }
        fetchBooks()
    }

    private fun fetchBooks(){
        swipeRefresh.isRefreshing = true

        CoroutineScope(Dispatchers.Main).launch {

            try{
               booksViewModel.books.observe(this@MainActivity, Observer { response ->
                   when(response){
                       is Resource.Success ->{
                           swipeRefresh.isRefreshing = false
                           response.data?.let { booksResponse ->
                            showBooks(booksResponse)

                           }
                       }



                       is Resource.Loading ->{
                           Toast.makeText(this@MainActivity,
                   "Please wait:",
                  Toast.LENGTH_LONG).show()
                       }

                       is Resource.Error ->{
                           Toast.makeText(this@MainActivity,
                               "Error Occurred:",
                               Toast.LENGTH_LONG).show()
                       }

                   }

               })
            }catch (e:Exception){
                Toast.makeText(this@MainActivity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG).show()

            }            }

//                val response = booksViewModel.getAllBooks()
//                if(response.isSuccessful && response.body() != null){
//                  swipeRefresh.isRefreshing = false
//                  val data = response.body()
//                  data?.let{
//                      showBooks(it)
//                  }
//                }else{
//
//                }
//
//            } catch(e:Exception){
//                Toast.makeText(this@MainActivity,
//                    "Error Occurred: ${e.message}",
//                    Toast.LENGTH_LONG).show()
//            }
//        }



        }

    private fun showBooks(book: BooksResponse) {
        recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        recyclerViewBooks.adapter = BooksAdapter(book,this)
    }
}


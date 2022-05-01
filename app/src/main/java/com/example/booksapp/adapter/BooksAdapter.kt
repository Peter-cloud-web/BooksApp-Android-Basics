package com.example.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksapp.MainActivity
import com.example.booksapp.databinding.BookItemBinding
import com.example.booksapp.model.BooksResponse

class BooksAdapter(private val book: BooksResponse, mainActivity: MainActivity): RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.MyViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksAdapter.MyViewHolder, position: Int) {
        with(holder) {
            with(book.books[position]) {
                binding.tvTitle.text = this.title
                binding.tvPrice.text = this.price.toString()

                Glide.with(binding.imgBookCover)
                    .load(this.image)
                    .into(binding.imgBookCover)

            }
        }
    }

    override fun getItemCount(): Int {
        return book.books.size
    }

    inner class MyViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {


    }
}


package com.example.books.presentation.adapter

import com.example.books.domain.model.Book

interface BookClickListener {

    fun onBookClickListener(book: Book)
}
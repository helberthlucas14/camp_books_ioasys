package com.example.books.domain.repositories

import com.example.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    fun getBooks(query: String?): Flow<List<Book>>
    fun saveBooks(books: List<Book>)
}
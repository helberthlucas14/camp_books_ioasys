package com.example.books.data.data_source.local

import com.example.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookLocalDataSource {

    fun getAccessToken(): Flow<String>
    fun saveBooks(bookList: List<Book>)
    fun getBooks(query: String?): Flow<List<Book>>
}
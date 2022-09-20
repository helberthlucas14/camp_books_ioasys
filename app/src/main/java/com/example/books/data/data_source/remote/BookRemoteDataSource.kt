package com.example.books.data.data_source.remote

import com.example.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRemoteDataSource {
    fun getBooks(accessToken: String, query: String?): Flow<List<Book>>
}
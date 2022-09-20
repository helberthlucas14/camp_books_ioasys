package com.example.books.data_remote.data_source

import com.example.books.data.data_source.remote.BookRemoteDataSource
import com.example.books.data_remote.mappers.toDomain
import com.example.books.data_remote.service.BookService
import com.example.books.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookDataSourceImpl(
    private val bookService: BookService
) : BookRemoteDataSource {

    override fun getBooks(accessToken: String, query: String?): Flow<List<Book>> = flow {

        val response = bookService.getBooks(
            accessToken = "Bearer $accessToken",
            page = 1
        )

        if (response.isSuccessful) {
            response.body()?.data?.let { bookList ->
                query?.let {
                    emit(bookList.filter { book ->
                        book.name?.trim()?.contains(it, true) ?: false
                    }.toDomain())
                } ?: kotlin.run {
                    emit(bookList.toDomain())
                }
            }

        }
    }
}
package com.example.books.data.repositories

import com.example.books.data.data_source.local.BookLocalDataSource
import com.example.books.data.data_source.remote.BookRemoteDataSource
import com.example.books.domain.model.Book
import com.example.books.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepositoryImpl(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource,
) : BooksRepository {

    override fun getBooks(query: String?): Flow<List<Book>> = flow {

        bookLocalDataSource.getAccessToken().collect { token ->

            if (token.isNotEmpty()) {
                bookRemoteDataSource.getBooks(token, query).collect { booklist ->
                    emit(booklist)
                }
            } else {
                bookLocalDataSource.getBooks(query = query).collect { books ->
                    emit(books)
                }
            }
        }
    }

    override fun saveBooks(books: List<Book>) = bookLocalDataSource.saveBooks(
        bookList = books
    )
}
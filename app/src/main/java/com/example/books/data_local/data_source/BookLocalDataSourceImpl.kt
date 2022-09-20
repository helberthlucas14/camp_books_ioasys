package com.example.books.data_local.data_source

import com.example.books.data.data_source.local.BookLocalDataSource
import com.example.books.data_local.database.BookDao
import com.example.books.data_local.mappers.toDao
import com.example.books.data_local.mappers.toDomain
import com.example.books.data_local.utils.LocalConstants.ACCESS_TOKEN_KEY
import com.example.books.data_local.utils.SharedPreferencesHelper
import com.example.books.data_remote.mappers.toDomain
import com.example.books.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookLocalDataSourceImpl(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val bookDao: BookDao
) : BookLocalDataSource {

    override fun getAccessToken(): Flow<String> = flow {
        emit(sharedPreferencesHelper.getString(ACCESS_TOKEN_KEY))
    }

    override fun saveBooks(bookList: List<Book>) = bookDao.addBooks(
        bookList = bookList.map { it.toDao() }
    )

    override fun getBooks(query: String?): Flow<List<Book>> = flow {

        var bookList = bookDao.getBooks().map { it.toDomain() }

        query?.let {
            emit(bookList.filter { book ->
                book.name.trim().contains(it, true)
            })
        } ?: kotlin.run {
            emit(bookList)
        }
    }
}
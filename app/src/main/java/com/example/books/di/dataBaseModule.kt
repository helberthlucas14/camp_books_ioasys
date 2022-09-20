package com.example.books.di

import androidx.room.Room
import com.example.books.data_local.database.BookDatabase
import com.example.books.data_local.utils.LocalConstants.BOOK_DATABASE
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            BookDatabase::class.java,
            BOOK_DATABASE
        ).build()
    }

    single { get<BookDatabase>().bookDao() }
}
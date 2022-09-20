package com.example.books.di

import com.example.books.data.repositories.BookRepositoryImpl
import com.example.books.data.repositories.LoginRepositoryImpl
import com.example.books.domain.repositories.BooksRepository
import com.example.books.domain.repositories.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

var dataModule = module {

    single { CoroutineScope(Dispatchers.IO) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<BooksRepository> { BookRepositoryImpl(get(), get()) }

}
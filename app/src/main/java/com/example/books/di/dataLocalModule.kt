package com.example.books.di

import com.example.books.data.data_source.local.BookLocalDataSource
import com.example.books.data.data_source.local.LoginLocalDataSource
import com.example.books.data_local.data_source.BookLocalDataSourceImpl
import com.example.books.data_local.data_source.LoginLocalDataSourceImpl
import com.example.books.data_local.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {

    single<BookLocalDataSource> { BookLocalDataSourceImpl(get(), get()) }
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get()) }
    single { SharedPreferencesHelper(androidContext()) }
}
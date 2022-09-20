package com.example.books.di

import com.example.books.data.data_source.remote.BookRemoteDataSource
import com.example.books.data.data_source.remote.LoginRemoteDataSource
import com.example.books.data_remote.service.AuthService
import com.example.books.data_remote.service.BookService
import com.example.books.data_remote.utils.ApiConstants
import com.example.books.data_remote.utils.WebServiceFactory
import com.example.books.data_remote.data_source.BookDataSourceImpl
import com.example.books.data_remote.data_source.LoginRemoteDataSourceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module

val dataRemote = module {

    single<AuthService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = ApiConstants.BASE_URL
        )
    }
    single<BookService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = ApiConstants.BASE_URL
        )
    }

    single<OkHttpClient> { WebServiceFactory.providerOkHttpClient() }
    single<LoginRemoteDataSource> { LoginRemoteDataSourceImpl(get()) }
    single<BookRemoteDataSource> { BookDataSourceImpl(get()) }
}
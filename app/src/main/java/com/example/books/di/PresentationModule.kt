package com.example.books.di

import com.example.books.presentation.viewmodel.BookListViewModel
import com.example.books.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { BookListViewModel(get(), get()) }
}
package com.example.books.di

import com.example.books.domain.usecase.GetBookListUseCase
import com.example.books.domain.usecase.LoginUseCase
import com.example.books.domain.usecase.SaveListBookUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { LoginUseCase(get(), get()) }
    factory { SaveListBookUseCase(get(), get()) }
    factory { GetBookListUseCase(get(), get()) }
}

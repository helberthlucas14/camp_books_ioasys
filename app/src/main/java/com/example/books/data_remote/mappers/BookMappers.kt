package com.example.books.data_remote.mappers

import com.example.books.data_remote.model.BookResponse
import com.example.books.domain.model.Book

fun List<BookResponse>.toDomain(): List<Book> {
    return this.map { response ->
        Book(
            id = response.id ?: "",
            name = response.name ?: "",
            author = response.author?.first() ?: "",
            pages = response.pages ?: "",
            editor = response.editor ?: "",
            date = response.date ?: "",
            isbn10 = response.isbn10 ?: "",
            isbn13 = response.isbn13 ?: "",
            language = response.language ?: "",
            review = response.review ?: "",
            imageUrl = response.imageUrl ?: "",
        )
    }
}
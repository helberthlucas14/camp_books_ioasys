package com.example.books.data_local.mappers

import com.example.books.data_local.model.BookDataLocal
import com.example.books.domain.model.Book

fun Book.toDao(): BookDataLocal = BookDataLocal(
    id = this.id,
    name = this.name,
    author = this.author,
    pages = this.pages,
    editor = this.editor,
    date = this.date,
    isbn10 = this.isbn10,
    isbn13 = this.isbn13,
    language = this.language,
    review = this.review,
    imageUrl = this.imageUrl
)

fun BookDataLocal.toDomain(): Book = Book(
    id = this.id,
    name = this.name ?: "",
    author = this.author ?: "",
    pages = this.pages ?: "",
    editor = this.editor ?: "",
    date = this.date ?: "",
    isbn10 = this.isbn10 ?: "",
    isbn13 = this.isbn13 ?: "",
    language = this.language ?: "",
    review = this.review ?: "",
    imageUrl = this.imageUrl ?: ""
)
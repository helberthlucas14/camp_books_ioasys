package com.example.books.domain.model

data class Book(
    val id: String,
    val name: String,
    val author: String,
    val pages: String,
    val editor: String,
    val date: String,
    val isbn10: String,
    val isbn13: String?,
    val language: String,
    val review: String,
    val imageUrl: String,
)
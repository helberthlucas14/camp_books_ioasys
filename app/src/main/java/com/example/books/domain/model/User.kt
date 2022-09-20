package com.example.books.domain.model

data class User(
    val id: String,
    val name: String,
    val birthDate: String?,
    val gender: String,
    val accessToken: String,
)
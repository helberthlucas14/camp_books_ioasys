package com.example.books.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookDataLocal(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val author: String? = null,
    val pages: String? = null,
    val editor: String? = null,
    val date: String? = null,
    val isbn10: String? = null,
    val isbn13: String? = null,
    val language: String? = null,
    val review: String? = null,
    val imageUrl: String? = null,
    val category: String? = null,
)

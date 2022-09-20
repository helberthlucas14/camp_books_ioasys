package com.example.books.data_remote.model

import com.google.gson.annotations.SerializedName

data class BookDataResponse(
    @SerializedName("data")
    val data: List<BookResponse>
)

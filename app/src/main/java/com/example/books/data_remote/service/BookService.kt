package com.example.books.data_remote.service

import com.example.books.data_remote.model.BookDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookService {

    @GET("books")
    suspend fun getBooks(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int
    ): Response<BookDataResponse>
}
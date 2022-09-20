package com.example.books.data.data_source.local

interface LoginLocalDataSource {

    fun saveAccessToken(accessToken: String)
}
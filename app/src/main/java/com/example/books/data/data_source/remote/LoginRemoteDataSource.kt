package com.example.books.data.data_source.remote

import com.example.books.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRemoteDataSource {

    fun login(email: String, password: String): Flow<User>

}
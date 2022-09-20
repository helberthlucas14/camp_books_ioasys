package com.example.books.domain.repositories

import com.example.books.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login(email: String, password: String): Flow<User>

}
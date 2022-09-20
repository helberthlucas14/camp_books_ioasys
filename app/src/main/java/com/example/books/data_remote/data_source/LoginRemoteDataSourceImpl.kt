package com.example.books.data_remote.data_source

import com.example.books.data.data_source.remote.LoginRemoteDataSource
import com.example.books.data_remote.mappers.toDomain
import com.example.books.data_remote.model.LoginRequest
import com.example.books.data_remote.service.AuthService
import com.example.books.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRemoteDataSourceImpl(
    private val authService: AuthService
) : LoginRemoteDataSource {

    override fun login(email: String, password: String): Flow<User> = flow {
        val response = authService.signIn(LoginRequest(email, password))
        val accessToken = response.headers()["authorization"]
        if (response.isSuccessful) {
            response.body()?.let { loginResponse ->
                emit(loginResponse.toDomain(accessToken ?: ""))
            }
        }
    }
}
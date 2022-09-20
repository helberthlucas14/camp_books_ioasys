package com.example.books.data.repositories

import com.example.books.data.data_source.local.LoginLocalDataSource
import com.example.books.data.data_source.remote.LoginRemoteDataSource
import com.example.books.domain.model.User
import com.example.books.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun login(email: String, password: String): Flow<User> = flow {
        loginRemoteDataSource.login(email, password).collect { userData ->
            loginLocalDataSource.saveAccessToken(accessToken = userData.accessToken)

            emit(userData)
        }
    }
}
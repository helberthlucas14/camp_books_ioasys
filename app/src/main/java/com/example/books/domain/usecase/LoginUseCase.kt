package com.example.books.domain.usecase

import com.example.books.domain.exception.InvalidEmailException
import com.example.books.domain.exception.InvalidPasswordException
import com.example.books.domain.model.User
import com.example.books.domain.repositories.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository,
    scope: CoroutineScope
) : UseCase<LoginUseCase.Params, User>(scope = scope) {

    data class Params(
        val email: String,
        val password: String
    )

    override fun run(params: Params?): Flow<User> = when {
        params?.email?.isEmpty() == true -> throw InvalidEmailException()
        params?.password?.isEmpty() == true -> throw InvalidPasswordException()
        else -> loginRepository.login(
            email = params?.email ?: "",
            password = params?.password ?: ""
        )
    }
}
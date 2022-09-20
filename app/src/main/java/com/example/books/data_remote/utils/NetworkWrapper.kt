package com.example.books.data_remote.utils

import com.example.books.data_remote.exception.InvalidDataException
import com.example.books.data_remote.exception.NotFoundException
import com.example.books.data_remote.exception.UnauthorizedException
import com.example.books.data_remote.exception.UnknownNetworkException
import retrofit2.Response

suspend fun <T> requestWrapper(
    noContent: Boolean = false,
    call: suspend () -> Response<T>
): T {
    call().let { result ->
        return when {
            result.isSuccessful.not() -> throw handleExceptionByCode(result)
            noContent -> Unit as T
            else -> result.body() ?: throw NoContentException()
        }
    }
}

@Suppress("MagicNumber", "ThrowsCount")
private fun handleExceptionByCode(response: Response<*>): Exception {
    when (response.code()) {
        400 -> throw UnauthorizedException(message = response.errorBody()?.string())
        401 -> throw InvalidDataException()
        404 -> throw NotFoundException()
        else -> throw UnknownNetworkException()
    }
}
package com.example.books.data_remote.utils

import java.io.IOException

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Sem conexão com a Internet."),
    TIMEOUT_ERROR(" Temos muitos acessos no momento. Por favor, volte mais tarde."),
    GENERIC_ERROR("Estamos trabalhando para corrigir este erro.")
}

data class NetworkException(
    val msg: String? = ErrorMessageEnum.GENERIC_ERROR.value,
    val code: Int? = null
) : Exception(msg) {
    override var message = msg
}

open class NoContentException : IOException()
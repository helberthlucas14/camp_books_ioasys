package com.example.books.data_remote.exception

class UnknownNetworkException(message: String? = null, title: String? = null) :
    RuntimeException(message, Throwable(title))
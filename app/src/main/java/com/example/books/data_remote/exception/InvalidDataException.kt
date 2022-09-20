package com.example.books.data_remote.exception

class InvalidDataException (message: String? = null, title: String? = null) :
    RuntimeException(message, Throwable(title))
package com.example.books.domain.exception

open class LoginException : Throwable()

class InvalidEmailException : LoginException()
class InvalidPasswordException : LoginException()
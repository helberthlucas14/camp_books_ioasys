package com.example.books.data_remote.mappers

import com.example.books.data_remote.model.LoginResponse
import com.example.books.domain.model.User

fun LoginResponse.toDomain(accessToken: String) = User(
    id = this.id,
    name = this.name,
    birthDate = this.birthDate,
    gender = this.gender,
    accessToken = accessToken
)
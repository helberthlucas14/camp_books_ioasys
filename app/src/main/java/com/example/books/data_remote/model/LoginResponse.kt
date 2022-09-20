package com.example.books.data_remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("gender")
    val gender: String,
)

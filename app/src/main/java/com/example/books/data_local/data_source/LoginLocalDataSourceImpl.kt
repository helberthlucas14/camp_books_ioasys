package com.example.books.data_local.data_source

import com.example.books.data.data_source.local.LoginLocalDataSource
import com.example.books.data_local.utils.LocalConstants.ACCESS_TOKEN_KEY
import com.example.books.data_local.utils.SharedPreferencesHelper

class LoginLocalDataSourceImpl(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : LoginLocalDataSource {

    override fun saveAccessToken(accessToken: String) = sharedPreferencesHelper.saveString(
        key = ACCESS_TOKEN_KEY,
        value = accessToken
    )
}
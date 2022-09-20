package com.example.books.data_local.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.books.data_local.utils.LocalConstants.SHARED_PREFERENCES_NAME

class SharedPreferencesHelper(
    context: Context
) {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_NAME,
        MODE_PRIVATE
    )

    fun saveString(key: String, value: String) = sharedPreferences.edit().run {
        putString(key, value)
        apply()
    }

    fun getString(key: String): String = sharedPreferences.getString(key, "") ?: ""
}
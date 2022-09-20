package com.example.books.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.domain.exception.LoginException
import com.example.books.domain.model.User
import com.example.books.domain.repositories.LoginRepository
import com.example.books.domain.usecase.LoginUseCase
import com.example.books.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loggedUserViewState = MutableLiveData<ViewState<User>>()
    val loggedUserViewState = _loggedUserViewState as LiveData<ViewState<User>>

    fun login(email: String, password: String) {

        viewModelScope.launch {
            _loggedUserViewState.postLoading()

            loginUseCase(
                params = LoginUseCase.Params(
                    email.trim(),
                    password.trim()
                ),
                onSucess = {
                    _loggedUserViewState.postSuccess(it)
                },
                onError = {
                    _loggedUserViewState.postError(it)
                }
            )
        }
    }

    fun resetViewState() {
        _loggedUserViewState.postNeutral()
    }
}
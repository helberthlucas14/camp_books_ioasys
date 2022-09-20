package com.example.books.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    postValue(ViewState.Success(data))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    postValue(ViewState.Loading)
}

fun <T> MutableLiveData<ViewState<T>>.postError(throwable: Throwable) {
    postValue(ViewState.Error(throwable))
}


fun <T> MutableLiveData<ViewState<T>>.postNeutral() {
    postValue(ViewState.Neutral)
}
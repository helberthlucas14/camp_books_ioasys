package com.example.books.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.domain.exception.EmptyBookListException
import com.example.books.domain.model.Book
import com.example.books.domain.usecase.GetBookListUseCase
import com.example.books.domain.usecase.SaveListBookUseCase
import com.example.books.util.ViewState
import com.example.books.util.postError
import com.example.books.util.postLoading
import com.example.books.util.postSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListViewModel(
    private val getBookListUseCase: GetBookListUseCase,
    private val saveListBookUseCase: SaveListBookUseCase
) : ViewModel() {

    private val _bookListViewState = MutableLiveData<ViewState<List<Book>>>()
    val bookListViewState = _bookListViewState as LiveData<ViewState<List<Book>>>

    fun search(query: String = "") {
        viewModelScope.launch {
            _bookListViewState.postLoading()

            getBookListUseCase(
                params = GetBookListUseCase.Params(
                    query = query
                ),
                onSucess = {
                    saveBooks(books = it)
                    _bookListViewState.postSuccess(it)
                },
                onError = {
                    _bookListViewState.postError(it)
                }
            )
        }
    }

    private fun saveBooks(books: List<Book>) {
        saveListBookUseCase(
            params = SaveListBookUseCase.Params(
                books = books
            )
        )
    }
}
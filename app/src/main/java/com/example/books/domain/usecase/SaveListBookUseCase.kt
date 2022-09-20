package com.example.books.domain.usecase

import com.example.books.domain.model.Book
import com.example.books.domain.repositories.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SaveListBookUseCase(
    private val booksRepository: BooksRepository,
    scope: CoroutineScope
) : UseCase<SaveListBookUseCase.Params, Unit>(scope = scope) {

    data class Params(val books: List<Book>)

    override fun run(params: Params?): Flow<Unit> = flowOf(
        booksRepository.saveBooks(
            books = params?.books ?: listOf()
        )
    )
}
package com.example.books.domain.usecase

import com.example.books.domain.model.Book
import com.example.books.domain.repositories.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetBookListUseCase(
    private val booksRepository: BooksRepository,
    scope: CoroutineScope
) : UseCase<GetBookListUseCase.Params, List<Book>>(scope = scope) {

    data class Params(
        val query: String?
    )

    override fun run(params: Params?): Flow<List<Book>> = booksRepository.getBooks(
        query = params?.query
    )
}
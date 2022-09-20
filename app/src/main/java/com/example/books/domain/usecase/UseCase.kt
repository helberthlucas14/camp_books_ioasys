package com.example.books.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out T>(
    private val scope: CoroutineScope
) {

    abstract fun run(params: Params?): Flow<T>

    operator fun invoke(
        params: Params,
        onSucess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) {
        scope.launch(Dispatchers.IO) {
            try {
                run(params = params).collect {
                    withContext(Dispatchers.Main) {
                        onSucess(it)
                    }
                }
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    onError(error)
                }
            }
        }
    }
}
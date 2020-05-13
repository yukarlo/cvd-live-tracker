package com.yukarlo.usecase

import com.yukarlo.usecase.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class ResultUseCase<Type, in Params> where Type : Any {

    protected abstract val workDispatcher: CoroutineDispatcher

    abstract suspend fun run(params: Params): Result<Type>

    suspend operator fun invoke(params: Params): Result<Type> = withContext(workDispatcher) {
        run(params)
    }
}

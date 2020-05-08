package com.yukarlo.lib.cases.domain

import com.yukarlo.dispatchers.AppCoroutineDispatchers
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import com.yukarlo.usecase.NoResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RefreshCasesSummaryUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesRepository: ICvdCasesRepository
) : NoResultUseCase<Unit>() {

    override val workDispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io

    override suspend fun run(params: Unit) {
       mCvdCasesRepository.getSummary()
    }
}
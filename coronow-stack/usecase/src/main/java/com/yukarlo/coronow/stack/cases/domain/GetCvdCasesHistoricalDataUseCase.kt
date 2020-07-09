package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.usecase.FlowUseCase
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCvdCasesHistoricalDataUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesRemoteRepository: ICvdCasesRemoteRepository
) : FlowUseCase<CasesHistoryModel, Unit>() {

    override fun doWork(params: Unit): Flow<CasesHistoryModel> = flow {
        emit(mCvdCasesRemoteRepository.getHistoricalData())
    }

    override val dispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io
}

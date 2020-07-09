package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.usecase.FlowUseCase
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCvdCasesCountryHistoricalDataUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesRemoteRepository: ICvdCasesRemoteRepository
) : FlowUseCase<CasesCountryHistoryModel, String>() {

    override fun doWork(params: String): Flow<CasesCountryHistoryModel> = flow {
        emit(mCvdCasesRemoteRepository.getCountryHistoricalData(country = params))
    }

    override val dispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io
}

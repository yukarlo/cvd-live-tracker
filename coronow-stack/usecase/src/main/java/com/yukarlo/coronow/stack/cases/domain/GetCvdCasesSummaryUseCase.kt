package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.core.usecase.FlowUseCase
import com.yukarlo.coronow.stack.local.repository.ICvdCasesLocalRepository
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCvdCasesSummaryUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesRemoteRepository: ICvdCasesRemoteRepository,
    private val mCvdCasesLocalRepository: ICvdCasesLocalRepository
) : FlowUseCase<CasesSummaryModel, Unit>() {

    override fun doWork(params: Unit): Flow<CasesSummaryModel> = flow {
        mCvdCasesLocalRepository.getSummary()
            ?.let {
                emit(it)
            } ?: kotlin.run {
            val summary = mCvdCasesRemoteRepository.getSummary()
            mCvdCasesLocalRepository.addSummary(casesSummary = summary)
            emit(summary)
        }
    }

    override val dispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io
}

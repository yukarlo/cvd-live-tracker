package com.yukarlo.lib.cases.domain

import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCvdCasesSummaryUseCase @Inject constructor(
    private val mCvdCasesRepository: ICvdCasesRepository
) {
    fun execute(): Flow<CasesSummaryModel> = mCvdCasesRepository.getSummary()
}

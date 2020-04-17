package com.yukarlo.lib.cases.domain

import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCvdCasesSummaryUseCase @Inject constructor(
    private val mCvdCasesRepository: ICvdCasesRepository
) {
    fun execute(): Flow<CasesSummaryResponseModel> = mCvdCasesRepository.getSummary()
}

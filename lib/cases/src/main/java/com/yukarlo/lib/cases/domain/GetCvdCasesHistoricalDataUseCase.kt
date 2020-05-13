package com.yukarlo.lib.cases.domain

import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCvdCasesHistoricalDataUseCase @Inject constructor(
    private val mCvdCasesRepository: ICvdCasesRepository
) {
    fun execute(): Flow<CasesHistoryModel> = mCvdCasesRepository.getHistoricalData()
}

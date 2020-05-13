package com.yukarlo.lib.cases.domain

import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCvdCasesCountryHistoricalDataUseCase @Inject constructor(
    private val mCvdCasesRepository: ICvdCasesRepository
) {
    fun execute(parameter: String): Flow<CasesCountryHistoryModel> =
        mCvdCasesRepository.getCountryHistoricalData(country = parameter)
}

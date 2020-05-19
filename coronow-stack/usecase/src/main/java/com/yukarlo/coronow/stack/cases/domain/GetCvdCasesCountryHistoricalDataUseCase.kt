package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCvdCasesCountryHistoricalDataUseCase @Inject constructor(
    private val mCvdCasesRemoteRepository: ICvdCasesRemoteRepository
) {
    fun execute(parameter: String): Flow<CasesCountryHistoryModel> =
        mCvdCasesRemoteRepository.getCountryHistoricalData(country = parameter)
}

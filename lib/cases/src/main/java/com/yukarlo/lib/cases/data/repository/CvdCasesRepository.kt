package com.yukarlo.lib.cases.data.repository

import com.yukarlo.lib.cases.data.api.CvdCasesApiService
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CvdCasesRepository @Inject constructor(
    private val mCvdCasesApiService: CvdCasesApiService
) : ICvdCasesRepository {
    override fun getSummary(): Flow<CasesSummaryResponseModel> =
        flow {
            emit(mCvdCasesApiService.getAll())
        }
}

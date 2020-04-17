package com.yukarlo.lib.cases.domain.repository

import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import kotlinx.coroutines.flow.Flow

interface ICvdCasesRepository {
    fun getSummary(): Flow<CasesSummaryResponseModel>
}

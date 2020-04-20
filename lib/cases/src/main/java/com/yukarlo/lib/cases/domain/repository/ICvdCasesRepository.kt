package com.yukarlo.lib.cases.domain.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import kotlinx.coroutines.flow.Flow

interface ICvdCasesRepository {
   fun getSummary(): Flow<CasesSummaryModel>

   fun getContinents(): Flow<List<CasesContinentsModel>>
}

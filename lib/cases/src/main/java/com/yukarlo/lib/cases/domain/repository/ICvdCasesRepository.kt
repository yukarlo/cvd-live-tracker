package com.yukarlo.lib.cases.domain.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import kotlinx.coroutines.flow.Flow

interface ICvdCasesRepository {
    fun getSummary(): Flow<CasesSummaryModel>

    fun getHistoricalData(): Flow<CasesHistoryModel>

    fun getCountryHistoricalData(country: String): Flow<CasesCountryHistoryModel>

    fun getContinents(): Flow<List<CasesContinentsModel>>

    fun getAllCountries(): Flow<List<CasesCountriesModel>>
}

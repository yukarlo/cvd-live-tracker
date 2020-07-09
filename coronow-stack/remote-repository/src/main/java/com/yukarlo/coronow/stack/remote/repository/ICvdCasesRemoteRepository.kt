package com.yukarlo.coronow.stack.remote.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel

interface ICvdCasesRemoteRepository {
    suspend fun getSummary(): CasesSummaryModel

    suspend fun getHistoricalData(): CasesHistoryModel

    suspend fun getCountryHistoricalData(country: String): CasesCountryHistoryModel

    suspend fun getContinents(): List<CasesContinentsModel>

    suspend fun getAllCountries(): List<CasesCountriesModel>
}

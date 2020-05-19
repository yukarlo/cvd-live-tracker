package com.yukarlo.coronow.stack.local.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesSummaryModel

interface ICvdCasesLocalRepository {
    suspend fun addCountries(countries: List<CasesCountriesModel>)
    suspend fun addContinents(continents: List<CasesContinentsModel>)
    suspend fun addSummary(casesSummary: CasesSummaryModel)
    fun getCountries(): List<CasesCountriesModel>
    fun getContinents(): List<CasesContinentsModel>
    fun getSummary(): CasesSummaryModel?
}

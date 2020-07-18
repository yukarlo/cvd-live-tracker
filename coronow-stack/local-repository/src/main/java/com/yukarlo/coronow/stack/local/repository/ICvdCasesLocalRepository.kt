package com.yukarlo.coronow.stack.local.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.core.domain.model.FavoriteCountry
import com.yukarlo.core.domain.model.SortBy

interface ICvdCasesLocalRepository {
    suspend fun addOrUpdateCountries(countries: List<CasesCountriesModel>)
    suspend fun markCountryAsFavorite(country: FavoriteCountry)
    suspend fun addContinents(continents: List<CasesContinentsModel>)
    suspend fun addSummary(casesSummary: CasesSummaryModel)
    fun getCountries(sortBy: SortBy): List<CasesCountriesModel>
    fun getContinents(): List<CasesContinentsModel>
    fun getSummary(): CasesSummaryModel?
}

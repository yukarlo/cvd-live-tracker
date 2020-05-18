package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesCountriesModel(
    val countryName: String,
    val countryIso: String,
    val continent: String,
    val totalCasesCount: String,
    val totalTodayCases: String,
    val totalDeceasedCount: String,
    val totalTodayDeceased: String,
    val totalRecoveredCount: String,
    val totalActiveCount: String,
    val countryFlag: String
) : DomainModel
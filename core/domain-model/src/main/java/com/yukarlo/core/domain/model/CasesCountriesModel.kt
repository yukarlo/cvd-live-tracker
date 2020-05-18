package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesCountriesModel(
    val countryName: String,
    val countryIso: String,
    val totalCasesCount: String,
    val totalDeceasedCount: String,
    val totalRecoveredCount: String,
    val totalActiveCount:String,
    val countryFlag: String
) : DomainModel
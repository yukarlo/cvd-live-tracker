package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesCountriesModel(
    val countryName: String,
    val totalCasesCount: String,
    val totalDeceasedCount: String,
    val totalRecoveredCount: String
) : DomainModel
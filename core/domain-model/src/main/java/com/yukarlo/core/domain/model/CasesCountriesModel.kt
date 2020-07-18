package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesCountriesModel(
    val countryName: String,
    val countryIso: String,
    val continent: String,
    val totalCasesCount: Long,
    val totalTodayCases: Long,
    val totalDeceasedCount: Long,
    val totalTodayDeceased: Long,
    val totalRecoveredCount: Long,
    val totalActiveCount: Long,
    val countryFlag: String,
    val isFavorite: Boolean = false,
) : DomainModel
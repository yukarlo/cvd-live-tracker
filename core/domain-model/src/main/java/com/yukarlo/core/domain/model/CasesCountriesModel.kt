package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesCountriesModel(
    val countryName: String = "",
    val countryIso: String = "",
    val continent: String = "",
    val totalCasesCount: Long = 0,
    val totalTodayCases: Long = 0,
    val totalDeceasedCount: Long = 0,
    val totalTodayDeceased: Long = 0,
    val totalRecoveredCount: Long = 0,
    val totalActiveCount: Long = 0,
    val countryFlag: String = "",
    val isFavorite: Boolean = false,
    val tests: Long = 0,
    val critical: Long = 0,
    val updatedSince: Long = 0
) : DomainModel

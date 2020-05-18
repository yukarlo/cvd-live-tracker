package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesSummaryModel(
    val totalCasesCount: Long,
    val totalDeceasedCount: Long,
    val totalRecoveredCount: Long,
    val affectedCountries: Long,
    val updatedSince: String
) : DomainModel
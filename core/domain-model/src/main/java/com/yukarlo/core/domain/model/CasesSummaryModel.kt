package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesSummaryModel(
    val totalCasesCount: String,
    val totalDeceasedCount: String,
    val totalRecoveredCount: String,
    val updatedSince: String
) : DomainModel
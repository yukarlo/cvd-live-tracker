package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesContinentsModel(
    val continentName: String,
    val totalCasesCount: String,
    val totalDeceasedCount: String,
    val totalRecoveredCount: String,
    val totalActiveCount: String,
    val totalCriticalCount: String
) : DomainModel
package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesContinentsModel(
    val continentName: String,
    val totalCasesCount: Long,
    val totalDeceasedCount: Long,
    val totalRecoveredCount: Long,
    val totalActiveCount: Long,
    val totalCriticalCount: Long
) : DomainModel
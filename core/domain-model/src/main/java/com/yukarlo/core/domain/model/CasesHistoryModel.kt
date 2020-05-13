package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesHistoryModel(
    val casesHistory: Map<String, Float>,
    val deathsHistory: Map<String, Float>,
    val recoveredHistory: Map<String, Float>
) : DomainModel

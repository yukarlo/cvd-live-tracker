package com.yukarlo.core.domain.model

import com.yukarlo.core.domain.DomainModel

data class CasesCountryHistoryModel(
    val country: String,
    val province: String,
    val countryTimeline: CasesHistoryModel
) : DomainModel

package com.yukarlo.core.domain.model

sealed class SortBy {
    object Country : SortBy()
    object Confirmed : SortBy()
    object Recovered : SortBy()
    object Deceased : SortBy()
    object Active : SortBy()
}
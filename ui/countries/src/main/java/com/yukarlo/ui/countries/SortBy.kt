package com.yukarlo.ui.countries

sealed class SortBy {
    object Country : SortBy()
    object Confirmed : SortBy()
    object Recovered : SortBy()
    object Deceased : SortBy()
    object Active : SortBy()
}
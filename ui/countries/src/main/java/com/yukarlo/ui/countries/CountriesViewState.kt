package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewState
import com.yukarlo.core.domain.model.SortBy
import com.yukarlo.core.domain.model.CasesCountriesModel

internal data class CountriesViewState(
    val fetchStatus: FetchStatus = FetchStatus.Idle,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val countries: List<CasesCountriesModel> = listOf(),
    val sortBy: SortBy = SortBy.Country
) : BaseViewState

internal enum class FetchStatus {
    Fetching,
    Fetched,
    Idle
}

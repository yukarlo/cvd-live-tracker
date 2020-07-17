package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewState
import com.yukarlo.core.domain.model.CasesCountriesModel

internal data class CountriesViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val countries: List<CasesCountriesModel> = listOf(),
    val sortBy: SortBy = SortBy.Country
) : BaseViewState

package com.yukarlo.ui.country.details

import com.yukarlo.base.BaseViewState
import com.yukarlo.core.domain.model.CasesCountriesModel

internal data class CountryDetailsViewState(
    val fetchStatus: FetchStatus = FetchStatus.Idle,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val details: CasesCountriesModel = CasesCountriesModel()
) : BaseViewState

internal enum class FetchStatus {
    Fetching,
    Fetched,
    Idle
}

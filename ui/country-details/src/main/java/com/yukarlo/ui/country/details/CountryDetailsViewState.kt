package com.yukarlo.ui.country.details

import com.yukarlo.base.BaseViewState
import com.yukarlo.core.domain.model.CasesCountriesModel

data class CountryDetailsViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val details: CasesCountriesModel = CasesCountriesModel()
) : BaseViewState
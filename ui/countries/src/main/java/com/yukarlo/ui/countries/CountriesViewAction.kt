package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewAction
import com.yukarlo.core.domain.model.CasesCountriesModel

internal sealed class CountriesViewAction : BaseViewAction {
    object CountriesLoading : CountriesViewAction()
    class CountriesLoadSuccess(val countries: List<CasesCountriesModel>) : CountriesViewAction()
    object CountriesLoadFailure : CountriesViewAction()
}

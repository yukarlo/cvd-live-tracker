package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewAction

internal sealed class CountriesViewAction : BaseViewAction {
    object InitialLoad : CountriesViewAction()
    object RefreshData : CountriesViewAction()
    data class SortCountriesBy(val sortBy: SortBy) : CountriesViewAction()
    data class FilterCountries(val query: String) : CountriesViewAction()
}

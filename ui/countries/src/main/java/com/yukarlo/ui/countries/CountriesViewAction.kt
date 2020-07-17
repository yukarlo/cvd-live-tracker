package com.yukarlo.ui.countries

internal sealed class CountriesViewAction {
    object InitialLoad : CountriesViewAction()
    object RefreshData : CountriesViewAction()
    data class SortCountriesBy(val sortBy: SortBy) : CountriesViewAction()
    data class FilterCountries(val query: String) : CountriesViewAction()
}

package com.yukarlo.ui.countries

sealed class CountriesViewEvent {
    object RefreshData : CountriesViewEvent()
    data class SortCountriesBy(val sortBy: SortBy) : CountriesViewEvent()
    data class FilterCountries(val query: String) : CountriesViewEvent()
}

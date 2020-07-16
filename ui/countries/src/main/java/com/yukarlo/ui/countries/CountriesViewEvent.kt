package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewEvent

sealed class CountriesViewEvent: BaseViewEvent {
    object RefreshData : CountriesViewEvent()
    data class SortedBy(val sortBy: SortBy) : CountriesViewEvent()
    data class ContinentName(val continentName: String) : CountriesViewEvent()
}

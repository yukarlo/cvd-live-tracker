package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewEvent
import com.yukarlo.core.domain.model.CasesCountriesModel

sealed class CountriesViewEvent : BaseViewEvent {
    object CountriesLoading : CountriesViewEvent()
    object CountriesLoadFailure : CountriesViewEvent()
    class CountriesLoadSuccess(val countries: List<CasesCountriesModel>) : CountriesViewEvent()
    class CountriesSortedBy(val sortedBy: SortBy) : CountriesViewEvent()
}

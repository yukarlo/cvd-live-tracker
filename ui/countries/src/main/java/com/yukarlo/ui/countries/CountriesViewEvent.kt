package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewEvent

sealed class CountriesViewEvent: BaseViewEvent {
    data class ContinentName(val continentName: String) : CountriesViewEvent()
}

package com.yukarlo.ui.countries

import androidx.navigation.NavDirections

sealed class CountriesViewSideEffect {
    data class NavigateTo(val directions: NavDirections) : CountriesViewSideEffect()
    data class UpdateCountryName(val name: String): CountriesViewSideEffect()
}

package com.yukarlo.ui.countries

import com.yukarlo.core.domain.model.FavoriteCountry

interface ICountryInteraction {
    fun addToFavorites(country: FavoriteCountry)
    fun navigateToCountryDetails(countryIso: String)
}

package com.yukarlo.ui.countries

import com.yukarlo.core.domain.model.FavoriteCountry

interface ICountryFavoriteInteraction {
    fun addToFavorites(country: FavoriteCountry)
}

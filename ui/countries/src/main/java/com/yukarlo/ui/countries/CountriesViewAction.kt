package com.yukarlo.ui.countries

import com.yukarlo.base.BaseViewAction
import com.yukarlo.core.domain.model.FavoriteCountry
import com.yukarlo.core.domain.model.SortBy

internal sealed class CountriesViewAction : BaseViewAction {
    object InitialLoad : CountriesViewAction()
    object RefreshData : CountriesViewAction()
    data class SortCountriesBy(val sortBy: SortBy) : CountriesViewAction()
    data class AddToFavorite(val country: FavoriteCountry) : CountriesViewAction()
    data class FilterCountries(val query: String) : CountriesViewAction()
}

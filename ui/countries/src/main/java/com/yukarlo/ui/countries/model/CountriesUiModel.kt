package com.yukarlo.ui.countries.model

import com.yukarlo.core.domain.model.SortBy

internal data class CountriesUiModel(
    var sortBy: SortBy = SortBy.Country
)
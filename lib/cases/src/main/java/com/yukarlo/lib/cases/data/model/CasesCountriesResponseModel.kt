package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json

data class CasesCountriesResponseModel(
    @Json(name="countries")
    val countries: List<CasesCountryResponseModel>
)

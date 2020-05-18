package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json

internal data class CountryInfo(
    @Json(name = "flag")
    val flag: String,

    @Json(name = "iso2")
    val iso2: String?,

    @Json(name = "iso3")
    val iso3: String
)

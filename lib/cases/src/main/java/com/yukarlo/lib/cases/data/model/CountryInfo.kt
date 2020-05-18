package com.yukarlo.lib.cases.data.model

import com.google.gson.annotations.SerializedName

internal data class CountryInfo(
    @SerializedName(value = "flag")
    val flag: String,

    @SerializedName(value = "iso2")
    val iso2: String,

    @SerializedName(value = "iso3")
    val iso3: String
)

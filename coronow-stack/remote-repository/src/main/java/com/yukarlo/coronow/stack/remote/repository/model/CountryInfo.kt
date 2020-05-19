package com.yukarlo.coronow.stack.remote.repository.model

import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName(value = "flag")
    val flag: String,

    @SerializedName(value = "iso2")
    val iso2: String?,

    @SerializedName(value = "iso3")
    val iso3: String?
)

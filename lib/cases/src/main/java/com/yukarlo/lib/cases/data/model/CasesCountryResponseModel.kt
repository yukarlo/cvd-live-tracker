package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json

internal data class CasesCountryResponseModel(
    @Json(name = "updated")
    val updated: Int,

    @Json(name = "country")
    val country: String,

    @Json(name = "countryInfo")
    val countryInfo: CountryInfo,

    @Json(name = "cases")
    val cases: Int,

    @Json(name = "todayCases")
    val casesToday: Int,

    @Json(name = "deaths")
    val deaths: Int,

    @Json(name = "todayDeaths")
    val deathsToday: Int,

    @Json(name = "recovered")
    val recovered: Int,

    @Json(name = "active")
    val active: Int,

    @Json(name = "critical")
    val critical: Int,

    @Json(name = "tests")
    val tests: Int
)

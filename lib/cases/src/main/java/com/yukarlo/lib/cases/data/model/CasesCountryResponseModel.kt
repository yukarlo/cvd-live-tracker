package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json
import com.yukarlo.stack.network.model.DataModel

internal data class CasesCountryResponseModel(
    @Json(name = "updated")
    val updated: Long,

    @Json(name = "country")
    val country: String,

    @Json(name = "countryInfo")
    val countryInfo: CountryInfo,

    @Json(name = "cases")
    val cases: Long,

    @Json(name = "todayCases")
    val casesToday: Long,

    @Json(name = "deaths")
    val deaths: Long,

    @Json(name = "todayDeaths")
    val deathsToday: Long,

    @Json(name = "recovered")
    val recovered: Long,

    @Json(name = "active")
    val active: Long,

    @Json(name = "critical")
    val critical: Long,

    @Json(name = "tests")
    val tests: Long
) : DataModel

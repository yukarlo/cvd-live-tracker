package com.yukarlo.lib.cases.data.model

import com.google.gson.annotations.SerializedName
import com.yukarlo.stack.network.model.DataModel

internal data class CasesCountryResponseModel(
    @SerializedName(value = "updated")
    val updated: Long,

    @SerializedName(value = "country")
    val country: String,

    @SerializedName(value = "countryInfo")
    val countryInfo: CountryInfo,

    @SerializedName(value = "cases")
    val cases: Long,

    @SerializedName(value = "todayCases")
    val casesToday: Long,

    @SerializedName(value = "deaths")
    val deaths: Long,

    @SerializedName(value = "todayDeaths")
    val deathsToday: Long,

    @SerializedName(value = "recovered")
    val recovered: Long,

    @SerializedName(value = "active")
    val active: Long,

    @SerializedName(value = "critical")
    val critical: Long,

    @SerializedName(value = "tests")
    val tests: Long
) : DataModel

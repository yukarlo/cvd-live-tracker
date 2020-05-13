package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json
import com.yukarlo.stack.network.model.DataModel

data class CasesContinentsResponseModel(
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

    @Json(name = "continent")
    val continent: String
) : DataModel
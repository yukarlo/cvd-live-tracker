package com.yukarlo.coronow.stack.remote.repository.model

import com.google.gson.annotations.SerializedName
import com.yukarlo.core.network.model.DataModel

data class CasesContinentsResponseModel(
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

    @SerializedName(value = "continent")
    val continent: String
) : DataModel

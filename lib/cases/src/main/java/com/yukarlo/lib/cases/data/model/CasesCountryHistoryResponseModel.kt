package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json
import com.yukarlo.stack.network.model.DataModel

internal data class CasesCountryHistoryResponseModel(
    @Json(name = "country")
    val country: String,

    @Json(name = "province")
    val province: String?,

    @Json(name = "timeline")
    val timeline: CasesHistoryResponseModel
) : DataModel

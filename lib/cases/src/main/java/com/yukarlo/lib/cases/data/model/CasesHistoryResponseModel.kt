package com.yukarlo.lib.cases.data.model

import com.squareup.moshi.Json
import com.yukarlo.stack.network.model.DataModel

internal data class CasesHistoryResponseModel(
    @Json(name = "cases")
    val cases: LinkedHashMap<String, Int>,

    @Json(name = "deaths")
    val deaths: LinkedHashMap<String, Int>,

    @Json(name = "recovered")
    val recovered: LinkedHashMap<String, Int>
) : DataModel

package com.yukarlo.lib.cases.data.model

import com.google.gson.annotations.SerializedName
import com.yukarlo.stack.network.model.DataModel

internal data class CasesHistoryResponseModel(
    @SerializedName(value = "cases")
    val cases: LinkedHashMap<String, Int>,

    @SerializedName(value = "deaths")
    val deaths: LinkedHashMap<String, Int>,

    @SerializedName(value = "recovered")
    val recovered: LinkedHashMap<String, Int>
) : DataModel

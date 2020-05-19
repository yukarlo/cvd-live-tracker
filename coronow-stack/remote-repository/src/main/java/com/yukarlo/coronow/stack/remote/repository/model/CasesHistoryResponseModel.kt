package com.yukarlo.coronow.stack.remote.repository.model

import com.google.gson.annotations.SerializedName
import com.yukarlo.core.network.model.DataModel

data class CasesHistoryResponseModel(
    @SerializedName(value = "cases")
    val cases: LinkedHashMap<String, Int>,

    @SerializedName(value = "deaths")
    val deaths: LinkedHashMap<String, Int>,

    @SerializedName(value = "recovered")
    val recovered: LinkedHashMap<String, Int>
) : DataModel

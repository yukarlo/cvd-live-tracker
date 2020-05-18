package com.yukarlo.lib.cases.data.model

import com.google.gson.annotations.SerializedName
import com.yukarlo.stack.network.model.DataModel

internal data class CasesCountryHistoryResponseModel(
    @SerializedName(value = "country")
    val country: String,

    @SerializedName(value = "province")
    val province: String?,

    @SerializedName(value = "timeline")
    val timeline: CasesHistoryResponseModel
) : DataModel

package com.yukarlo.coronow.stack.remote.repository.model

import com.google.gson.annotations.SerializedName
import com.yukarlo.core.network.model.DataModel

data class CasesCountryHistoryResponseModel(
    @SerializedName(value = "country")
    val country: String,

    @SerializedName(value = "province")
    val province: String?,

    @SerializedName(value = "timeline")
    val timeline: CasesHistoryResponseModel
) : DataModel

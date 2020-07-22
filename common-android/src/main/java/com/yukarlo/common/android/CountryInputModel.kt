package com.yukarlo.common.android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInputModel(
    val mCountryIso: String
) : Parcelable

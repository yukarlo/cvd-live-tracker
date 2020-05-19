package com.yukarlo.common.android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesInputModel(
    val mContinentName: String
) : Parcelable

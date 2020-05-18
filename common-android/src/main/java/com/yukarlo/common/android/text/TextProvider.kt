package com.yukarlo.common.android.text

import android.content.Context
import com.yukarlo.common.android.R
import java.text.NumberFormat
import javax.inject.Inject

class TextProvider @Inject constructor(
    val context: Context
) {
    fun formatNumber(value: Long): String {
        val numberFormat = NumberFormat.getNumberInstance()
        return numberFormat.format(value)
    }

    fun provideCasesTodayString(value: Long): String {
        return if (value > 0) {
            "+${formatNumber(value = value)}  ${context.getString(R.string.today)}"
        } else {
            ""
        }
    }
}
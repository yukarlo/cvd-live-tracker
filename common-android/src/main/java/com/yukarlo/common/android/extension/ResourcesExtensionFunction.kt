package com.yukarlo.common.android.extension

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

fun Resources.getDrawableCompat(@DrawableRes id: Int) = ResourcesCompat.getDrawable(
    this,
    id,
    null
)

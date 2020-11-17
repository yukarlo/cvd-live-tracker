package com.yukarlo.common.android.compose.utils

import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb

/**
 * Copied from android compose samples
 */
interface SystemUiController {
    fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = blackScrimmed
    )

    fun setNavigationBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = blackScrimmed
    )

    fun setSystemBarsColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = blackScrimmed
    )
}

fun systemUiController(window: Window): SystemUiController {
    return SystemUiControllerImpl(window)
}

private class SystemUiControllerImpl(private val window: Window) : SystemUiController {

    override fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) {
        val statusBarColor = when {
            darkIcons -> transformColorForLightContent(color)
            else -> color
        }
        window.statusBarColor = statusBarColor.toArgb()

        if (Build.VERSION.SDK_INT >= 23) {
            @Suppress("DEPRECATION")
            if (darkIcons) {
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }

    override fun setNavigationBarColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) {
        val navBarColor = when {
            Build.VERSION.SDK_INT >= 29 -> Color.Transparent // For gesture nav
            darkIcons && Build.VERSION.SDK_INT < 26 -> transformColorForLightContent(color)
            else -> color
        }
        window.navigationBarColor = navBarColor.toArgb()

        if (Build.VERSION.SDK_INT >= 26) {
            @Suppress("DEPRECATION")
            if (darkIcons) {
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }

    override fun setSystemBarsColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) {
        setStatusBarColor(color, darkIcons, transformColorForLightContent)
        setNavigationBarColor(color, darkIcons, transformColorForLightContent)
    }
}

val sysUiController = staticAmbientOf<SystemUiController> {
    FakeSystemUiController
}

private val blackScrim = Color(0f, 0f, 0f, 0.2f) // 20% opaque black
private val blackScrimmed: (Color) -> Color = { original ->
    blackScrim.compositeOver(original)
}

private object FakeSystemUiController : SystemUiController {
    override fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) = Unit

    override fun setNavigationBarColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) = Unit

    override fun setSystemBarsColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) = Unit
}

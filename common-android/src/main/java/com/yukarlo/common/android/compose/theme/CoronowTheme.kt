package com.yukarlo.common.android.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.graphics.Color
import com.yukarlo.common.android.compose.utils.sysUiController

private val LightColorPalette = CoronowColors(
    cardCriticalBackground = yellow300,
    cardRecoveredBackground = green300,
    cardDeceasedBackground = red300,
    cardAdditionalInformationBackground = cyan300,
    uiBackground = white00,
    cardTextPrimary = gray00,
    textPrimary = white800,
    textSecondary = gray300,
    textLink = blue500,
    error = red300,
    isDark = false
)

private val DarkColorPalette = CoronowColors(
    uiBackground = gray1000,
    cardTextPrimary = gray00,
    textPrimary = white50,
    textSecondary = white800,
    textLink = blue800,
    error = red500,
    isDark = true,
    cardCriticalBackground = yellow500,
    cardRecoveredBackground = green500,
    cardDeceasedBackground = red500,
    cardAdditionalInformationBackground = cyan500
)

@Composable
fun CoronowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = sysUiController.current
    onCommit(sysUiController, colors.uiBackground) {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    }

    ProvideCoronowColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object CoronowTheme {
    @Composable
    val colors: CoronowColors
        get() = AmbientCoronowColors.current
}

/**
 * Jetsnack custom Color Palette
 */
@Stable
class CoronowColors(
    uiBackground: Color,
    cardTextPrimary: Color,
    textPrimary: Color,
    textSecondary: Color,
    textLink: Color,
    error: Color,
    isDark: Boolean,
    cardCriticalBackground: Color,
    cardRecoveredBackground: Color,
    cardDeceasedBackground: Color,
    cardAdditionalInformationBackground: Color
) {
    var uiBackground by mutableStateOf(uiBackground)
        private set
    var cardCriticalBackground by mutableStateOf(cardCriticalBackground)
        private set
    var cardRecoveredBackground by mutableStateOf(cardRecoveredBackground)
        private set
    var cardDeceasedBackground by mutableStateOf(cardDeceasedBackground)
        private set
    var cardAdditionalInformationBackground by mutableStateOf(cardAdditionalInformationBackground)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var cardTextPrimary by mutableStateOf(cardTextPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textLink by mutableStateOf(textLink)
        private set
    var error by mutableStateOf(error)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(colors: CoronowColors) {
        uiBackground = colors.uiBackground
        cardCriticalBackground = colors.cardCriticalBackground
        cardRecoveredBackground = colors.cardRecoveredBackground
        cardDeceasedBackground = colors.cardDeceasedBackground
        cardAdditionalInformationBackground = colors.cardAdditionalInformationBackground
        cardTextPrimary = colors.cardTextPrimary
        textPrimary = colors.textPrimary
        textSecondary = colors.textSecondary
        textLink = colors.textLink
        error = colors.error
        isDark = colors.isDark
    }
}

@Composable
fun ProvideCoronowColors(
    colors: CoronowColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    Providers(AmbientCoronowColors provides colorPalette, children = content)
}

private val AmbientCoronowColors = staticAmbientOf<CoronowColors> {
    error("No Coronow Color Palette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [CoronowTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)

package com.yukarlo.ui.home

import androidx.navigation.NavDirections

sealed class HomeViewSideEffect {
    data class NavigateTo(val directions: NavDirections) : HomeViewSideEffect()
}

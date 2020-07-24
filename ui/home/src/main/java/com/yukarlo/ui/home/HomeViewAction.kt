package com.yukarlo.ui.home

import androidx.navigation.NavDirections
import com.yukarlo.base.BaseViewAction

internal sealed class HomeViewAction : BaseViewAction {
    object InitialLoad : HomeViewAction()
    object Refresh : HomeViewAction()
    object Retry : HomeViewAction()
    data class Navigate(val directions: NavDirections) : HomeViewAction()
}

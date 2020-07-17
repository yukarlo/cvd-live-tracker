package com.yukarlo.ui.home

internal sealed class HomeViewAction {
    object InitialLoad: HomeViewAction()
    object Refresh : HomeViewAction()
    object Retry : HomeViewAction()
}

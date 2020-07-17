package com.yukarlo.ui.home

import com.yukarlo.base.BaseViewAction

internal sealed class HomeViewAction : BaseViewAction {
    object InitialLoad: HomeViewAction()
    object Refresh : HomeViewAction()
    object Retry : HomeViewAction()
}

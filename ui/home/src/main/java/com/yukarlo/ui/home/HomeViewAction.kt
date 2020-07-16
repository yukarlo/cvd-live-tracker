package com.yukarlo.ui.home

import com.yukarlo.base.BaseViewAction
import com.yukarlo.ui.home.adapter.model.HomeBaseItem

internal sealed class HomeViewAction : BaseViewAction {
    object HomeLoading : HomeViewAction()
    class HomeLoadSuccess(val homeItems: List<HomeBaseItem>) : HomeViewAction()
    object HomeLoadFailure : HomeViewAction()
}

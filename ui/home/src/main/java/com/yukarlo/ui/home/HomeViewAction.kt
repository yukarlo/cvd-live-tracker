package com.yukarlo.ui.home

import com.yukarlo.base.BaseViewAction
import com.yukarlo.ui.home.adapter.model.HomeBaseItem

internal sealed class Action : BaseViewAction {
    object HomeLoading : Action()
    class HomeLoadSuccess(val homeItems: List<HomeBaseItem>) : Action()
    object HomeLoadFailure : Action()
}

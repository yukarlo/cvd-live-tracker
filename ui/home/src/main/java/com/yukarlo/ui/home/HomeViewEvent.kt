package com.yukarlo.ui.home

import com.yukarlo.base.BaseViewEvent
import com.yukarlo.ui.home.adapter.model.HomeBaseItem

internal sealed class HomeViewEvent : BaseViewEvent {
    object HomeLoading : HomeViewEvent()
    class HomeLoadSuccess(val homeItems: List<HomeBaseItem>) : HomeViewEvent()
    object HomeLoadFailure : HomeViewEvent()
}
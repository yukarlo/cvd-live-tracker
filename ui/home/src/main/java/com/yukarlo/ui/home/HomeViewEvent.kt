package com.yukarlo.ui.home

import com.yukarlo.base.BaseViewEvent

sealed class HomeViewEvent : BaseViewEvent {
    object RefreshData : HomeViewEvent()
}
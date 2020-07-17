package com.yukarlo.ui.home

sealed class HomeViewEvent {
    object RefreshData : HomeViewEvent()
}
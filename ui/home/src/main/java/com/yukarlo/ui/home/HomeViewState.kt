package com.yukarlo.ui.home

import com.yukarlo.base.BaseViewState
import com.yukarlo.ui.home.adapter.model.HomeBaseItem

internal data class ViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val homeItems: List<HomeBaseItem> = listOf()
) : BaseViewState

package com.yukarlo.ui.home.adapter

import com.yukarlo.core.domain.model.CasesSummaryModel

internal sealed class HomeItem {
    internal data class SummaryItem(val summary: CasesSummaryModel) : HomeItem()
}
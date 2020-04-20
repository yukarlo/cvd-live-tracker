package com.yukarlo.ui.home.adapter.model

import com.yukarlo.ui.home.adapter.model.HomeBaseItem.ContinentsItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.SummaryItem

internal data class HomeAdapterItem(
    val summaryItem: SummaryItem?,
    val continentsItem: List<ContinentsItem>
)
package com.yukarlo.ui.home.adapter.model

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesSummaryModel

internal sealed class HomeBaseItem {
    internal data class SummaryItem(val summary: CasesSummaryModel) : HomeBaseItem()
    internal data class ContinentsItem(val continents: CasesContinentsModel) : HomeBaseItem()
    internal object ContinentsTitle : HomeBaseItem()
}
package com.yukarlo.lib.cases.data.converter

import android.text.format.DateUtils
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.stack.network.model.DataToDomainConverter
import java.text.NumberFormat

internal object CvdResponseConverter :
    DataToDomainConverter<CasesSummaryResponseModel, CasesSummaryModel> {
    override fun convert(input: CasesSummaryResponseModel): CasesSummaryModel {
        val numberFormat = NumberFormat.getNumberInstance()
        return CasesSummaryModel(
            totalCasesCount = numberFormat.format(input.cases),
            totalDeceasedCount = numberFormat.format(input.deaths),
            totalRecoveredCount = numberFormat.format(input.recovered),
            affectedCountries = input.affectedCountries.toString(),
            updatedSince = DateUtils.getRelativeTimeSpanString(input.updated).toString()
        )
    }
}

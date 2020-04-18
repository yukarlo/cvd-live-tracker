package com.yukarlo.lib.cases.data.converter

import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.stack.network.model.DataToDomainConverter

object CvdResponseConverter : DataToDomainConverter<CasesSummaryResponseModel, CasesSummaryModel> {
    override fun convert(input: CasesSummaryResponseModel): CasesSummaryModel {
        return CasesSummaryModel(
            totalCasesCount = input.cases.toString(),
            totalDeceasedCount = input.deaths.toString(),
            totalRecoveredCount = input.recovered.toString(),
            updatedSince = input.updated.toString()
        )
    }
}

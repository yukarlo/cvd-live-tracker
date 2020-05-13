package com.yukarlo.lib.cases.data.converter

import android.text.format.DateUtils
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.data.model.CasesContinentsResponseModel
import com.yukarlo.lib.cases.data.model.CasesCountryHistoryResponseModel
import com.yukarlo.lib.cases.data.model.CasesCountryResponseModel
import com.yukarlo.lib.cases.data.model.CasesHistoryResponseModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.stack.network.model.DataToDomainConverter
import java.text.NumberFormat

internal object CvdCasesResponseConverter :
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

internal object CvdContinentsResponseConverter :
    DataToDomainConverter<CasesContinentsResponseModel, CasesContinentsModel> {
    override fun convert(input: CasesContinentsResponseModel): CasesContinentsModel {
        val numberFormat = NumberFormat.getNumberInstance()
        return CasesContinentsModel(
            totalCasesCount = numberFormat.format(input.cases),
            totalDeceasedCount = numberFormat.format(input.deaths),
            totalRecoveredCount = numberFormat.format(input.recovered),
            continentName = input.continent
        )
    }
}

internal object CvdCountriesResponseConverter :
    DataToDomainConverter<CasesCountryResponseModel, CasesCountriesModel> {
    override fun convert(input: CasesCountryResponseModel): CasesCountriesModel {
        val numberFormat = NumberFormat.getNumberInstance()
        return CasesCountriesModel(
            totalCasesCount = numberFormat.format(input.cases),
            totalDeceasedCount = numberFormat.format(input.deaths),
            totalRecoveredCount = numberFormat.format(input.recovered),
            countryName = input.country,
            countryFlag = input.countryInfo.flag
        )
    }
}

internal object CvdHistoryResponseConverter :
    DataToDomainConverter<CasesHistoryResponseModel, CasesHistoryModel> {
    override fun convert(input: CasesHistoryResponseModel): CasesHistoryModel {
        val cases = input.cases.mapValues {
            it.value.toFloat()
        }

        val deaths = input.deaths.mapValues {
            it.value.toFloat()
        }

        val recovered = input.recovered.mapValues {
            it.value.toFloat()
        }

        return CasesHistoryModel(
            casesHistory = cases,
            deathsHistory = deaths,
            recoveredHistory = recovered
        )
    }
}

internal object CvdCountryHistoryResponseConverter :
    DataToDomainConverter<CasesCountryHistoryResponseModel, CasesCountryHistoryModel> {
    override fun convert(input: CasesCountryHistoryResponseModel): CasesCountryHistoryModel =
        CasesCountryHistoryModel(
            country = input.country,
            province = input.province ?: "",
            countryTimeline = CvdHistoryResponseConverter.convert(input = input.timeline)
        )
}

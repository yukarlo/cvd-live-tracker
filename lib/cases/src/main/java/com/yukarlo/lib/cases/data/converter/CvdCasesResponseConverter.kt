package com.yukarlo.lib.cases.data.converter

import android.text.format.DateUtils
import com.yukarlo.core.domain.model.*
import com.yukarlo.lib.cases.data.model.*
import com.yukarlo.stack.network.model.DataToDomainConverter
import java.text.NumberFormat

internal object CvdCasesResponseConverter :
    DataToDomainConverter<CasesSummaryResponseModel, CasesSummaryModel> {
    override fun convert(input: CasesSummaryResponseModel): CasesSummaryModel {
        return CasesSummaryModel(
            totalCasesCount = formatNumber(input.cases),
            totalDeceasedCount = formatNumber(input.deaths),
            totalRecoveredCount = formatNumber(input.recovered),
            affectedCountries = input.affectedCountries.toString(),
            updatedSince = DateUtils.getRelativeTimeSpanString(input.updated).toString()
        )
    }
}

internal object CvdContinentsResponseConverter :
    DataToDomainConverter<CasesContinentsResponseModel, CasesContinentsModel> {
    override fun convert(input: CasesContinentsResponseModel): CasesContinentsModel {
        return CasesContinentsModel(
            totalCasesCount = formatNumber(input.cases),
            totalDeceasedCount = formatNumber(input.deaths),
            totalRecoveredCount = formatNumber(input.recovered),
            totalActiveCount = formatNumber(input.active),
            totalCriticalCount = formatNumber(input.critical),
            continentName = input.continent
        )
    }
}

internal object CvdCountriesResponseConverter :
    DataToDomainConverter<CasesCountryResponseModel, CasesCountriesModel> {
    override fun convert(input: CasesCountryResponseModel): CasesCountriesModel {
        return CasesCountriesModel(
            totalCasesCount = formatNumber(input.cases),
            totalTodayCases = provideCasesTodayString(
                value = input.casesToday
            ),
            totalDeceasedCount = formatNumber(input.deaths),
            totalTodayDeceased = provideCasesTodayString(
                value = input.deathsToday
            ),
            totalRecoveredCount = formatNumber(input.recovered),
            totalActiveCount = formatNumber(input.active),
            countryName = input.country,
            continent = input.continent,
            countryIso = input.countryInfo.iso2 ?: "",
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

private fun formatNumber(value: Long): String {
    val numberFormat = NumberFormat.getNumberInstance()
    return numberFormat.format(value)
}

private fun provideCasesTodayString(value: Long): String {
    return if (value > 0) {
        "+${formatNumber(value = value)} today"
    } else {
        ""
    }
}

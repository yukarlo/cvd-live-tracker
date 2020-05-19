package com.yukarlo.coronow.stack.remote.repository.converter

import android.text.format.DateUtils
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.core.network.model.DataToDomainConverter
import com.yukarlo.coronow.stack.remote.repository.model.CasesContinentsResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesCountryHistoryResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesCountryResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesHistoryResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesSummaryResponseModel

internal object CvdCasesResponseConverter :
    DataToDomainConverter<CasesSummaryResponseModel, CasesSummaryModel> {
    override fun convert(input: CasesSummaryResponseModel): CasesSummaryModel {
        return CasesSummaryModel(
            totalCasesCount = input.cases,
            totalDeceasedCount = input.deaths,
            totalRecoveredCount = input.recovered,
            affectedCountries = input.affectedCountries,
            updatedSince = input.updated
        )
    }
}

internal object CvdContinentsResponseConverter :
    DataToDomainConverter<CasesContinentsResponseModel, CasesContinentsModel> {
    override fun convert(input: CasesContinentsResponseModel): CasesContinentsModel {
        return CasesContinentsModel(
            totalCasesCount = input.cases,
            totalDeceasedCount = input.deaths,
            totalRecoveredCount = input.recovered,
            totalActiveCount = input.active,
            totalCriticalCount = input.critical,
            continentName = input.continent
        )
    }
}

internal object CvdCountriesResponseConverter :
    DataToDomainConverter<CasesCountryResponseModel, CasesCountriesModel> {
    override fun convert(input: CasesCountryResponseModel): CasesCountriesModel {
        return CasesCountriesModel(
            totalCasesCount = input.cases,
            totalTodayCases = input.casesToday,
            totalDeceasedCount = input.deaths,
            totalTodayDeceased = input.deathsToday,
            totalRecoveredCount = input.recovered,
            totalActiveCount = input.active,
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

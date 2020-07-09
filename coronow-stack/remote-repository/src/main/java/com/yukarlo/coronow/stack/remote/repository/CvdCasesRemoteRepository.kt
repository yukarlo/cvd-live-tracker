package com.yukarlo.coronow.stack.remote.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.remote.repository.api.CvdCasesApiService
import com.yukarlo.coronow.stack.remote.repository.converter.CvdCasesResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdContinentsResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdCountriesResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdCountryHistoryResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdHistoryResponseConverter
import javax.inject.Inject

class CvdCasesRemoteRepository @Inject constructor(
    private val mCvdCasesApiService: CvdCasesApiService
) : ICvdCasesRemoteRepository {

    override suspend fun getSummary(): CasesSummaryModel =
        CvdCasesResponseConverter.convert(input = mCvdCasesApiService.getAll())

    override suspend fun getHistoricalData(): CasesHistoryModel =
        CvdHistoryResponseConverter.convert(input = mCvdCasesApiService.getHistoricalData())

    override suspend fun getCountryHistoricalData(country: String): CasesCountryHistoryModel =
        CvdCountryHistoryResponseConverter
            .convert(
                input = mCvdCasesApiService.getCountryHistoricalData(
                    country = country
                )
            )

    override suspend fun getContinents(): List<CasesContinentsModel> =
        mCvdCasesApiService.getContinents().map(CvdContinentsResponseConverter::convert)

    override suspend fun getAllCountries(): List<CasesCountriesModel> =
        mCvdCasesApiService.getCountries().map(CvdCountriesResponseConverter::convert)
}

package com.yukarlo.coronow.stack.remote.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.remote.repository.converter.CvdCasesResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdContinentsResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdCountriesResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdCountryHistoryResponseConverter
import com.yukarlo.coronow.stack.remote.repository.converter.CvdHistoryResponseConverter
import com.yukarlo.coronow.stack.remote.repository.api.CvdCasesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CvdCasesRemoteRepository @Inject constructor(
    private val mCvdCasesApiService: CvdCasesApiService
) : ICvdCasesRemoteRepository {
    override suspend fun getSummary(): CasesSummaryModel = CvdCasesResponseConverter.convert(input = mCvdCasesApiService.getAll())

    override fun getHistoricalData(): Flow<CasesHistoryModel> = flow {
        emit(CvdHistoryResponseConverter.convert(input = mCvdCasesApiService.getHistoricalData()))
    }

    override fun getCountryHistoricalData(country: String): Flow<CasesCountryHistoryModel> = flow {
        emit(
            CvdCountryHistoryResponseConverter
                .convert(
                    input = mCvdCasesApiService.getCountryHistoricalData(
                        country = country
                    )
                )
        )
    }

    override fun getContinents(): Flow<List<CasesContinentsModel>> = flow {
        emit(mCvdCasesApiService.getContinents().map(CvdContinentsResponseConverter::convert))
    }

    override fun getAllCountries(): Flow<List<CasesCountriesModel>> = flow {
        emit(mCvdCasesApiService.getCountries().map(CvdCountriesResponseConverter::convert))
    }
}

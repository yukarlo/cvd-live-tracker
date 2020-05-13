package com.yukarlo.lib.cases.data.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesCountryHistoryModel
import com.yukarlo.core.domain.model.CasesHistoryModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.data.api.CvdCasesApiService
import com.yukarlo.lib.cases.data.converter.CvdCasesResponseConverter
import com.yukarlo.lib.cases.data.converter.CvdContinentsResponseConverter
import com.yukarlo.lib.cases.data.converter.CvdCountriesResponseConverter
import com.yukarlo.lib.cases.data.converter.CvdCountryHistoryResponseConverter
import com.yukarlo.lib.cases.data.converter.CvdHistoryResponseConverter
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CvdCasesRepository @Inject constructor(
    private val mCvdCasesApiService: CvdCasesApiService
) : ICvdCasesRepository {
    override fun getSummary(): Flow<CasesSummaryModel> = flow {
        emit(CvdCasesResponseConverter.convert(input = mCvdCasesApiService.getAll()))
    }

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

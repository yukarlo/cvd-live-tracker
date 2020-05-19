package com.yukarlo.coronow.stack.remote.repository.api

import com.yukarlo.coronow.stack.remote.repository.model.CasesContinentsResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesCountryHistoryResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesCountryResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesHistoryResponseModel
import com.yukarlo.coronow.stack.remote.repository.model.CasesSummaryResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CvdCasesApiService {
    @GET("all")
    suspend fun getAll(): CasesSummaryResponseModel

    @GET("historical/all?lastdays=all")
    suspend fun getHistoricalData(): CasesHistoryResponseModel

    @GET("historical/{country}?lastdays=all")
    suspend fun getCountryHistoricalData(@Path("country") country: String): CasesCountryHistoryResponseModel

    @GET("countries?allowNull=false")
    suspend fun getCountries(): List<CasesCountryResponseModel>

    @GET("continents")
    suspend fun getContinents(): List<CasesContinentsResponseModel>
}

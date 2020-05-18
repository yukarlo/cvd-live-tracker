package com.yukarlo.lib.cases.data.api

import com.yukarlo.lib.cases.data.model.CasesContinentsResponseModel
import com.yukarlo.lib.cases.data.model.CasesCountryHistoryResponseModel
import com.yukarlo.lib.cases.data.model.CasesCountryResponseModel
import com.yukarlo.lib.cases.data.model.CasesHistoryResponseModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CvdCasesApiService {
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

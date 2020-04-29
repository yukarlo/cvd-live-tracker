package com.yukarlo.lib.cases.data.api

import com.yukarlo.lib.cases.data.model.CasesContinentsResponseModel
import com.yukarlo.lib.cases.data.model.CasesCountryResponseModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import retrofit2.http.GET

internal interface CvdCasesApiService {
    @GET("all")
    suspend fun getAll(): CasesSummaryResponseModel

    @GET("countries")
    suspend fun getCountries(): List<CasesCountryResponseModel>

    @GET("continents")
    suspend fun getContinents(): List<CasesContinentsResponseModel>
}

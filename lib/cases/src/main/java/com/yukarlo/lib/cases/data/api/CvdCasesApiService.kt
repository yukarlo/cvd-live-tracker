package com.yukarlo.lib.cases.data.api

import com.yukarlo.lib.cases.data.model.CasesCountriesResponseModel
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import retrofit2.http.GET

interface CvdCasesApiService {
    @GET("all")
    suspend fun getAll(): CasesSummaryResponseModel

    @GET("countries")
    suspend fun getCountries(): List<CasesCountriesResponseModel>
}

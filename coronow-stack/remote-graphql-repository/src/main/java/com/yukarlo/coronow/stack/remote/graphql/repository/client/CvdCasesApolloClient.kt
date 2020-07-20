package com.yukarlo.coronow.stack.remote.graphql.repository.client

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import query.GetContinentsQuery
import query.GetCountriesQuery
import query.GetHistoricalDataQuery
import query.GetSummaryQuery
import javax.inject.Inject

class CvdCasesApolloClient @Inject constructor(
    private val mApolloClient: ApolloClient
) {
    suspend fun getSummary(): GetSummaryQuery.All? =
        mApolloClient.query(GetSummaryQuery()).toDeferred().await().data?.all

    suspend fun getCountries(): List<GetCountriesQuery.Country?>? =
        mApolloClient.query(GetCountriesQuery()).toDeferred().await().data?.countries

    suspend fun getContinents(): List<GetContinentsQuery.Continent?>? =
        mApolloClient.query(GetContinentsQuery()).toDeferred().await().data?.continents

    suspend fun getHistory(countryName: String): GetHistoricalDataQuery.Country? =
        mApolloClient.query(GetHistoricalDataQuery(countryName = countryName)).toDeferred()
            .await().data?.country
}

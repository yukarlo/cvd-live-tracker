package com.yukarlo.coronow.stack.remote.graphql.repository

import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.remote.graphql.repository.client.CvdCasesApolloClient
import query.GetSummaryQuery
import javax.inject.Inject

class CvdCasesGraphqlRepository @Inject constructor(
    private val mCvdCasesApolloClient: CvdCasesApolloClient
) : ICvdCasesGraphqlRepository {

    override suspend fun getSummary(): CasesSummaryModel {
        val summary: GetSummaryQuery.All? = mCvdCasesApolloClient.getSummary()
        return CasesSummaryModel(
            totalCasesCount = summary?.cases?.toLong() ?: 0L,
            totalDeceasedCount = summary?.deaths?.toLong() ?: 0L,
            totalRecoveredCount = summary?.recovered?.toLong() ?: 0L,
            affectedCountries = 0,
            updatedSince = summary?.updated?.toLong() ?: 0L
        )
    }
}

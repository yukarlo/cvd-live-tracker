package com.yukarlo.coronow.stack.remote.graphql.repository

import com.yukarlo.core.domain.model.CasesSummaryModel

interface ICvdCasesGraphqlRepository {
    suspend fun getSummary(): CasesSummaryModel
}

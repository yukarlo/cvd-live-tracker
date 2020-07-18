package com.yukarlo.coronow.stack.remote.graphql.repository.client

import com.apollographql.apollo.ApolloClient
import javax.inject.Inject

class CvdApolloClient @Inject constructor(
    private val mApolloClient: ApolloClient
) {
}

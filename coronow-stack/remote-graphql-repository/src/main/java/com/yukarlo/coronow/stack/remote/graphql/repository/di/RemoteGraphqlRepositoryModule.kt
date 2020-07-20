package com.yukarlo.coronow.stack.remote.graphql.repository.di

import com.apollographql.apollo.ApolloClient
import com.yukarlo.core.network.di.NetworkModule.Companion.BASE_URL_GRAPHQL
import com.yukarlo.coronow.stack.remote.graphql.repository.CvdCasesGraphqlRepository
import com.yukarlo.coronow.stack.remote.graphql.repository.ICvdCasesGraphqlRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RemoteGraphqlRepositoryModule {
    @Binds
    abstract fun bindGraphqlRepository(
        mCvdCasesGraphqlRepository: CvdCasesGraphqlRepository
    ): ICvdCasesGraphqlRepository

    companion object {
        @Provides
        fun provideApolloClient(
            okHttpClient: OkHttpClient,
            @Named(BASE_URL_GRAPHQL) baseUrl: String
        ): ApolloClient = ApolloClient.builder()
            .serverUrl(baseUrl)
            .okHttpClient(okHttpClient)
            .build()

//        @Provides
//        fun provideCvdCasesApolloClient(
//            apolloClient: ApolloClient
//        ): CvdCasesApolloClient = CvdCasesApolloClient(mApolloClient = apolloClient)
    }
}
package com.yukarlo.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL_REST = "base_url_rest_api"
        const val BASE_URL_GRAPHQL = "base_url_graphql"
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    fun provideOkHttpBuilder(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Named(BASE_URL_REST)
    fun provideBaseUrl(): String = "https://disease.sh/v3/covid-19/"

    @Provides
    @Named(BASE_URL_GRAPHQL)
    fun provideGraphqlUrl(): String = "https://covidstat.info/graphql"
}

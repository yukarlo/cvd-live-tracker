package com.yukarlo.lib.cases.di

import com.yukarlo.lib.cases.data.api.CvdCasesApiService
import com.yukarlo.lib.cases.data.repository.CvdCasesRepository
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class CvdCasesModule {
    @Binds
    abstract fun bindMovieRepository(
        mCvdCasesRepository: CvdCasesRepository
    ): ICvdCasesRepository

    companion object {

        @Provides
        fun provideCvdCasesApiService(
            okHttpClient: OkHttpClient,
            baseUrl: String,
            moshiConverterFactory: MoshiConverterFactory
        ): CvdCasesApiService = Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(CvdCasesApiService::class.java)
    }
}

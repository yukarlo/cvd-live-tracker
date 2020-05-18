package com.yukarlo.lib.cases.di

import com.yukarlo.lib.cases.data.api.CvdCasesApiService
import com.yukarlo.lib.cases.data.repository.CvdCasesRepository
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
internal abstract class LibCvdCasesModule {
    @Binds
    abstract fun bindMovieRepository(
        mCvdCasesRepository: CvdCasesRepository
    ): ICvdCasesRepository

    companion object {

        @Provides
        fun provideCvdCasesApiService(
            okHttpClient: OkHttpClient,
            baseUrl: String,
            gsonConverterFactory: GsonConverterFactory
        ): CvdCasesApiService = Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(CvdCasesApiService::class.java)
    }
}

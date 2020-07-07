package com.yukarlo.coronow.stack.remote.repository.di

import com.yukarlo.coronow.stack.remote.repository.CvdCasesRemoteRepository
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import com.yukarlo.coronow.stack.remote.repository.api.CvdCasesApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RemoteRepositoryModule {
    @Binds
    abstract fun bindRemoteRepository(
        mCvdCasesRemoteRepository: CvdCasesRemoteRepository
    ): ICvdCasesRemoteRepository

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

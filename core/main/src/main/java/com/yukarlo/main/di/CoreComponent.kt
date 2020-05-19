package com.yukarlo.main.di

import android.content.Context
import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.dispatchers.di.DispatchersModule
import com.yukarlo.core.network.di.NetworkModule
import com.yukarlo.coronow.stack.database.Database
import com.yukarlo.coronow.stack.local.repository.ICvdCasesLocalRepository
import com.yukarlo.coronow.stack.local.repository.di.LocalRepositoryModule
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import com.yukarlo.coronow.stack.remote.repository.di.RemoteRepositoryModule
import com.yukarlo.main.CoroNowApplication
import dagger.BindsInstance
import dagger.Component
import di.DatabaseModule
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

@Component(
    modules = [
        NetworkModule::class,
        DispatchersModule::class,
        DatabaseModule::class,
        RemoteRepositoryModule::class,
        LocalRepositoryModule::class
    ]
)
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): CoreComponent
    }

    fun inject(coroNowApplication: CoroNowApplication)

    fun provideContext(): Context

    fun provideOkHttpClient(): OkHttpClient

    fun provideGsonConverterFactory(): GsonConverterFactory

    fun provideBaseUrl(): String

    fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers

    fun provideDatabase(): Database

    fun provideCvdCasesRemoteRepository(): ICvdCasesRemoteRepository

    fun provideCvdCasesLocalRepository(): ICvdCasesLocalRepository
}

package com.yukarlo.main.di

import android.content.Context
import com.yukarlo.dispatchers.AppCoroutineDispatchers
import com.yukarlo.dispatchers.di.DispatchersModule
import com.yukarlo.main.CoroNowApplication
import com.yukarlo.stack.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

@Component(
    modules = [
        NetworkModule::class,
        DispatchersModule::class
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
}

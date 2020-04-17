package com.yukarlo.core.di

import android.content.Context
import com.yukarlo.core.CoroNowApplication
import com.yukarlo.stack.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): CoreComponent
    }

    fun inject(coroNowApplication: CoroNowApplication)

    fun provideContext(): Context
}
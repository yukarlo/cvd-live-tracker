package com.yukarlo.ui.home.di

import androidx.lifecycle.ViewModel
import com.yukarlo.common.android.di.viewmodel.ViewModelKey
import com.yukarlo.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class UiHomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}

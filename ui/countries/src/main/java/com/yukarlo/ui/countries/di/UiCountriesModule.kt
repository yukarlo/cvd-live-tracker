package com.yukarlo.ui.countries.di

import androidx.lifecycle.ViewModel
import com.yukarlo.common.android.di.viewmodel.ViewModelKey
import com.yukarlo.ui.countries.CountriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class UiCountriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindCountriesViewModel(viewModel: CountriesViewModel): ViewModel
}

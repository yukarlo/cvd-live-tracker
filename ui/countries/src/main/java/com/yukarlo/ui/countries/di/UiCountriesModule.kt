package com.yukarlo.ui.countries.di

import androidx.lifecycle.ViewModel
import com.yukarlo.common.android.CountriesInputModel
import com.yukarlo.common.android.di.viewmodel.ViewModelKey
import com.yukarlo.ui.countries.CountriesFragment
import com.yukarlo.ui.countries.CountriesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class UiCountriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindCountriesViewModel(viewModel: CountriesViewModel): ViewModel

    companion object {
        @Provides
        fun provideInputModel(fragment: CountriesFragment) =
            fragment.arguments?.get("continent") as CountriesInputModel
    }
}

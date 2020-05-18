package com.yukarlo.ui.continents.di

import androidx.lifecycle.ViewModel
import com.yukarlo.common.android.ContinentInputModel
import com.yukarlo.common.android.di.viewmodel.ViewModelKey
import com.yukarlo.ui.continents.ContinentsFragment
import com.yukarlo.ui.continents.ContinentsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class UiContinentsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContinentsViewModel::class)
    abstract fun bindContinentsViewModel(viewModel: ContinentsViewModel): ViewModel

    companion object {
        @Provides
        fun provideInputModel(fragment: ContinentsFragment) =
            fragment.arguments?.get("continent") as ContinentInputModel
    }
}

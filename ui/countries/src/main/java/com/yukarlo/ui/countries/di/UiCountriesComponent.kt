package com.yukarlo.ui.countries.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.core.di.CoreComponent
import com.yukarlo.core.di.FeatureScope
import com.yukarlo.lib.cases.di.LibCvdCasesComponent
import com.yukarlo.ui.countries.CountriesFragment
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        CoreComponent::class,
        LibCvdCasesComponent::class
    ],
    modules = [
        ViewModelModule::class,
        UiCountriesModule::class
    ]
)
interface UiCountriesComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance countriesFragment: CountriesFragment,
            coreComponent: CoreComponent,
            libCvdCasesComponent: LibCvdCasesComponent
        ): UiCountriesComponent
    }

    fun inject(fragment: CountriesFragment)
}
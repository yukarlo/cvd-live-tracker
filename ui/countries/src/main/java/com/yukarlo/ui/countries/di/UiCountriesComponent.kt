package com.yukarlo.ui.countries.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.main.di.CoreComponent
import com.yukarlo.main.di.FeatureScope
import com.yukarlo.coronow.stack.cases.di.UseCaseComponent
import com.yukarlo.ui.countries.CountriesFragment
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        CoreComponent::class,
        UseCaseComponent::class
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
            useCaseComponent: UseCaseComponent
        ): UiCountriesComponent
    }

    fun inject(fragment: CountriesFragment)
}
package com.yukarlo.ui.continents.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.lib.cases.di.LibCvdCasesComponent
import com.yukarlo.main.di.CoreComponent
import com.yukarlo.main.di.FeatureScope
import com.yukarlo.ui.continents.ContinentsFragment
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
        UiContinentsModule::class
    ]
)
interface UiContinentsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance continentsFragment: ContinentsFragment,
            coreComponent: CoreComponent,
            libCvdCasesComponent: LibCvdCasesComponent
        ): UiContinentsComponent
    }

    fun inject(fragment: ContinentsFragment)
}

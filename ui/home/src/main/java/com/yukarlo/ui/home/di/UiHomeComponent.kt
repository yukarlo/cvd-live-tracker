package com.yukarlo.ui.home.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.main.di.CoreComponent
import com.yukarlo.main.di.FeatureScope
import com.yukarlo.lib.cases.di.LibCvdCasesComponent
import com.yukarlo.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

@com.yukarlo.main.di.FeatureScope
@Component(
    dependencies = [
        com.yukarlo.main.di.CoreComponent::class,
        LibCvdCasesComponent::class
    ],
    modules = [
        ViewModelModule::class,
        UiHomeModule::class
    ]
)
internal interface UiHomeComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance homeFragment: HomeFragment,
            coreComponent: com.yukarlo.main.di.CoreComponent,
            libCvdCasesComponent: LibCvdCasesComponent
        ): UiHomeComponent
    }

    fun inject(fragment: HomeFragment)
}


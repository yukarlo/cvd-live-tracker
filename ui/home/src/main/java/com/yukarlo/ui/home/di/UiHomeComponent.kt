package com.yukarlo.ui.home.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.coronow.stack.cases.di.UseCaseComponent
import com.yukarlo.main.di.CoreComponent
import com.yukarlo.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

@com.yukarlo.main.di.FeatureScope
@Component(
    dependencies = [
        CoreComponent::class,
        UseCaseComponent::class
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
            coreComponent: CoreComponent,
            useCaseComponent: UseCaseComponent
        ): UiHomeComponent
    }

    fun inject(fragment: HomeFragment)
}


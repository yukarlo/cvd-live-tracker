package com.yukarlo.ui.continents.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.coronow.stack.cases.di.UseCaseComponent
import com.yukarlo.main.di.CoreComponent
import com.yukarlo.main.di.FeatureScope
import com.yukarlo.ui.continents.ContinentsFragment
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
        UiContinentsModule::class
    ]
)
interface UiContinentsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance continentsFragment: ContinentsFragment,
            coreComponent: CoreComponent,
            useCaseComponent: UseCaseComponent
        ): UiContinentsComponent
    }

    fun inject(fragment: ContinentsFragment)
}

package com.yukarlo.ui.home.di

import com.yukarlo.common.android.di.viewmodel.ViewModelModule
import com.yukarlo.core.di.CoreComponent
import com.yukarlo.core.di.FeatureScope
import com.yukarlo.lib.cases.di.CvdCasesModule
import com.yukarlo.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        ViewModelModule::class,
        CvdCasesModule::class,
        UiHomeModule::class
    ]
)
internal interface UiHomeComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance homeFragment: HomeFragment,
            coreComponent: CoreComponent
        ): UiHomeComponent
    }

    fun inject(fragment: HomeFragment)
}


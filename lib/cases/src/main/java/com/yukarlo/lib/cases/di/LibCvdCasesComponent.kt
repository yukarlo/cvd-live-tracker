package com.yukarlo.lib.cases.di

import com.yukarlo.core.di.CoreComponent
import com.yukarlo.lib.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.lib.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [LibCvdCasesModule::class]
)
interface LibCvdCasesComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): LibCvdCasesComponent
    }

    fun provideGetCvdCasesSummaryUseCase(): GetCvdCasesSummaryUseCase

    fun provideGetCvdCasesContinentsUseCase(): GetCvdCasesContinentsUseCase

    fun provideCvdCasesRepository(): ICvdCasesRepository
}
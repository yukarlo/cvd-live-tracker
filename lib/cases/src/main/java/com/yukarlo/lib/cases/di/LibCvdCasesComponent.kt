package com.yukarlo.lib.cases.di

import com.yukarlo.main.di.CoreComponent
import com.yukarlo.lib.cases.domain.GetAllCountriesCasesUseCase
import com.yukarlo.lib.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.lib.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import dagger.Component

@Component(
    dependencies = [com.yukarlo.main.di.CoreComponent::class],
    modules = [LibCvdCasesModule::class]
)
interface LibCvdCasesComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: com.yukarlo.main.di.CoreComponent): LibCvdCasesComponent
    }

    fun provideGetCvdCasesSummaryUseCase(): GetCvdCasesSummaryUseCase

    fun provideGetCvdCasesContinentsUseCase(): GetCvdCasesContinentsUseCase

    fun provideGetGetAllCountriesCasesUseCase(): GetAllCountriesCasesUseCase

    fun provideCvdCasesRepository(): ICvdCasesRepository
}
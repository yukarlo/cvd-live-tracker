package com.yukarlo.coronow.stack.cases.di

import com.yukarlo.coronow.stack.cases.domain.GetAllCountriesCasesUseCase
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.main.di.CoreComponent
import dagger.Component

@Component(
    dependencies = [CoreComponent::class]
)
interface UseCaseComponent {
    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): UseCaseComponent
    }

    fun provideGetCvdCasesSummaryUseCase(): GetCvdCasesSummaryUseCase

    fun provideGetCvdCasesContinentsUseCase(): GetCvdCasesContinentsUseCase

    fun provideGetGetAllCountriesCasesUseCase(): GetAllCountriesCasesUseCase
}

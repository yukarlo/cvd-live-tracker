package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.usecase.FlowUseCase
import com.yukarlo.coronow.stack.local.repository.ICvdCasesLocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryDetailsUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesLocalRepository: ICvdCasesLocalRepository
) : FlowUseCase<CasesCountriesModel, String>() {
    override fun doWork(params: String): Flow<CasesCountriesModel> = flow {
        emit(mCvdCasesLocalRepository.getCountry(countryIso = params))
    }

    override val dispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io
}
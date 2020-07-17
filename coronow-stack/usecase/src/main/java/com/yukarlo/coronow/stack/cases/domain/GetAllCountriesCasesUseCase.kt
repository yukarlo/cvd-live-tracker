package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.usecase.FlowUseCase
import com.yukarlo.coronow.stack.local.repository.ICvdCasesLocalRepository
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCountriesCasesUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesRemoteRepository: ICvdCasesRemoteRepository,
    private val mCvdCasesLocalRepository: ICvdCasesLocalRepository
) : FlowUseCase<List<CasesCountriesModel>, Unit>() {

    override fun doWork(params: Unit): Flow<List<CasesCountriesModel>> = flow {
        mCvdCasesLocalRepository.getCountries()
            .takeIf { it.isNotEmpty() }
            ?.let {
                emit(it)
            } ?: kotlin.run {
            val countries = mCvdCasesRemoteRepository.getAllCountries()
            mCvdCasesLocalRepository.addOrUpdateCountries(countries = countries)
            emit(countries)
        }
    }

    override val dispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io
}

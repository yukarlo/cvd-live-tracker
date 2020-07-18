package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.SortBy
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
) : FlowUseCase<List<CasesCountriesModel>, SortBy>() {

    override fun doWork(params: SortBy): Flow<List<CasesCountriesModel>> = flow {
        mCvdCasesLocalRepository.getCountries(sortBy = params)
            .takeIf { it.isNotEmpty() }
            ?.let {
                emit(it)
            } ?: kotlin.run {
            mCvdCasesLocalRepository.addOrUpdateCountries(
                countries = mCvdCasesRemoteRepository.getAllCountries()
            )
            emit(mCvdCasesLocalRepository.getCountries(sortBy = params))
        }
    }

    override val dispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io
}

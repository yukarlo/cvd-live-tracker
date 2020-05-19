package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.coronow.stack.remote.repository.ICvdCasesRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCountriesCasesUseCase @Inject constructor(
    private val mCvdCasesRemoteRepository: ICvdCasesRemoteRepository
) {
    fun execute(): Flow<List<CasesCountriesModel>> = mCvdCasesRemoteRepository.getAllCountries()
}

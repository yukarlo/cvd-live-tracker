package com.yukarlo.lib.cases.domain

import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.lib.cases.domain.repository.ICvdCasesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCountriesCasesUseCase @Inject constructor(
    private val mCvdCasesRepository: ICvdCasesRepository
) {
    fun execute(): Flow<List<CasesCountriesModel>> = mCvdCasesRepository.getAllCountries()
}
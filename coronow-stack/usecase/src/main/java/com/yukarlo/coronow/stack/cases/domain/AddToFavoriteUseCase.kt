package com.yukarlo.coronow.stack.cases.domain

import com.yukarlo.core.dispatchers.AppCoroutineDispatchers
import com.yukarlo.core.domain.model.FavoriteCountry
import com.yukarlo.core.usecase.NoResultUseCase
import com.yukarlo.coronow.stack.local.repository.ICvdCasesLocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val mAppCoroutineDispatcher: AppCoroutineDispatchers,
    private val mCvdCasesLocalRepository: ICvdCasesLocalRepository
) : NoResultUseCase<FavoriteCountry>() {
    override val workDispatcher: CoroutineDispatcher
        get() = mAppCoroutineDispatcher.io

    override suspend fun run(params: FavoriteCountry) {
        mCvdCasesLocalRepository.markCountryAsFavorite(country = params)
    }

}
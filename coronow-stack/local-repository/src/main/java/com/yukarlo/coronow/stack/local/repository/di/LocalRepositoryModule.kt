package com.yukarlo.coronow.stack.local.repository.di

import com.yukarlo.coronow.stack.local.repository.CvdCasesLocalRepository
import com.yukarlo.coronow.stack.local.repository.ICvdCasesLocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class LocalRepositoryModule {

    @Binds
    abstract fun bindLocalRepository(
        mCvdCasesLocalRepository: CvdCasesLocalRepository
    ): ICvdCasesLocalRepository
}

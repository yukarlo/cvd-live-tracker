package com.yukarlo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.lib.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val mGetCvdCasesSummaryUseCase: GetCvdCasesSummaryUseCase,
    private val mGetCvdCasesContinentsUseCase: GetCvdCasesContinentsUseCase
) : ViewModel() {

    fun getSummary(): LiveData<CasesSummaryModel> =
        mGetCvdCasesSummaryUseCase.execute()
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    fun getContinents(): LiveData<List<CasesContinentsModel>> =
        mGetCvdCasesContinentsUseCase.execute()
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    fun getHomeData() =
        mGetCvdCasesContinentsUseCase.execute().combine(
            mGetCvdCasesSummaryUseCase.execute()
        ) { continents, summary ->
            provideHomeBaseItem(summary = summary, continents = continents)
        }.asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    private fun provideHomeBaseItem(
        summary: CasesSummaryModel,
        continents: List<CasesContinentsModel>
    ): List<HomeBaseItem> {
        val baseItem = mutableListOf<HomeBaseItem>()
        baseItem.add(HomeBaseItem.Header)
        baseItem.add(HomeBaseItem.SummaryItem(summary = summary))
        baseItem.add(HomeBaseItem.ContinentsHeader)
        continents.map {
            baseItem.add(HomeBaseItem.ContinentsItem(continents = it))
        }

        return baseItem
    }
}

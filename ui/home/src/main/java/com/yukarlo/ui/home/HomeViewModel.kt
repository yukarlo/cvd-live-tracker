package com.yukarlo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val mGetCvdCasesSummaryUseCase: GetCvdCasesSummaryUseCase,
    private val mGetCvdCasesContinentsUseCase: GetCvdCasesContinentsUseCase
) : ViewModel() {

    private val updateHome: MutableLiveData<List<HomeBaseItem>> = MutableLiveData()
    val onHomeUpdated: LiveData<List<HomeBaseItem>>
        get() = updateHome

    init {
        viewModelScope.launch(Dispatchers.IO) {
            refreshData()
        }
    }

    suspend fun refreshData() {
        combine(
            mGetCvdCasesContinentsUseCase.execute(),
            mGetCvdCasesSummaryUseCase.execute(params = Unit)
        ) { continents: List<CasesContinentsModel>, summary: CasesSummaryModel ->
            provideHomeBaseItem(summary = summary, continents = continents)
        }.collect { onHomeUpdate(homeItems = it) }
    }

    private fun provideHomeBaseItem(
        summary: CasesSummaryModel,
        continents: List<CasesContinentsModel>
    ): List<HomeBaseItem> = mutableListOf<HomeBaseItem>().apply {
        add(HomeBaseItem.Header)
        add(HomeBaseItem.SummaryItem(summary = summary))
        add(HomeBaseItem.HealthTipsItem)
        add(HomeBaseItem.ContinentsHeader)
        continents.map {
            add(HomeBaseItem.ContinentsItem(continents = it))
        }
    }

    private fun onHomeUpdate(homeItems: List<HomeBaseItem>) {
        updateHome.postValue(homeItems)
    }
}

package com.yukarlo.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.yukarlo.base.BaseViewModel
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.ui.home.HomeViewAction.HomeLoadFailure
import com.yukarlo.ui.home.HomeViewAction.HomeLoadSuccess
import com.yukarlo.ui.home.HomeViewAction.HomeLoading
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
internal class HomeViewModel @ViewModelInject constructor(
    private val mGetCvdCasesSummaryUseCase: GetCvdCasesSummaryUseCase,
    private val mGetCvdCasesContinentsUseCase: GetCvdCasesContinentsUseCase
) : BaseViewModel<HomeViewState, HomeViewAction>(HomeViewState()) {

    val intentChannel = ConflatedBroadcastChannel<HomeViewEvent>()

    init {
        viewModelScope.launch {
            intentChannel.send(HomeViewEvent.RefreshData)

            handleIntents()
        }
    }

    private suspend fun handleIntents() {
        intentChannel
            .asFlow()
            .collect { intent ->
                when (intent) {
                    is HomeViewEvent.RefreshData -> {
                        refreshData()
                    }
                }
            }
    }

    override fun onReduceState(viewAction: HomeViewAction): HomeViewState = when (viewAction) {
        is HomeLoading -> state.copy(
            isLoading = true,
            isError = false
        )
        is HomeLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            homeItems = viewAction.homeItems
        )
        is HomeLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            homeItems = listOf()
        )
    }

    private fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                combine(
                    mGetCvdCasesContinentsUseCase.execute(params = Unit),
                    mGetCvdCasesSummaryUseCase.execute(params = Unit)
                ) { continents: List<CasesContinentsModel>, summary: CasesSummaryModel ->
                    provideHomeBaseItem(summary = summary, continents = continents)
                }
                    .onStart { sendAction(HomeLoading) }
                    .collect {
                        sendAction(HomeLoadSuccess(homeItems = it))
                    }
            } catch (e: Exception) {
                sendAction(HomeLoadFailure)
            }
        }
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
}

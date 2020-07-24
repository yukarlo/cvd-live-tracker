package com.yukarlo.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.yukarlo.base.BaseViewModel
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesContinentsUseCase
import com.yukarlo.coronow.stack.cases.domain.GetCvdCasesSummaryUseCase
import com.yukarlo.ui.home.HomeViewEvent.HomeLoadSuccess
import com.yukarlo.ui.home.HomeViewEvent.HomeLoading
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
internal class HomeViewModel @ViewModelInject constructor(
    private val mGetCvdCasesSummaryUseCase: GetCvdCasesSummaryUseCase,
    private val mGetCvdCasesContinentsUseCase: GetCvdCasesContinentsUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent, HomeViewAction, HomeViewSideEffect>(HomeViewState()) {

    init {
        viewModelScope.launch {
            intentChannel.send(HomeViewAction.InitialLoad)
            handleIntents()
        }
    }

    // region Private Functions

    private suspend fun handleIntents() {
        intentChannel
            .asFlow()
            .collect { action ->
                when (action) {
                    is HomeViewAction.InitialLoad,
                    is HomeViewAction.Refresh,
                    is HomeViewAction.Retry -> loadData()
                    is HomeViewAction.Navigate -> sendSideEffect(
                        HomeViewSideEffect.NavigateTo(
                            directions = action.directions
                        )
                    )
                }
            }
    }

    override fun onReduceState(viewEvent: HomeViewEvent): HomeViewState = when (viewEvent) {
        is HomeLoading -> state.copy(
            isLoading = true,
            isError = false
        )
        is HomeLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            homeItems = viewEvent.homeItems
        )
        is HomeViewEvent.HomeLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            homeItems = listOf()
        )
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                mGetCvdCasesContinentsUseCase.execute(params = Unit),
                mGetCvdCasesSummaryUseCase.execute(params = Unit)
            ) { continents: List<CasesContinentsModel>, summary: CasesSummaryModel ->
                provideHomeBaseItem(summary = summary, continents = continents)
            }
                .onStart { sendEvent(viewEvent = HomeLoading) }
                .catch { sendEvent(viewEvent = HomeViewEvent.HomeLoadFailure) }
                .collect {
                    sendEvent(viewEvent = HomeLoadSuccess(homeItems = it))
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

    // endregion
}

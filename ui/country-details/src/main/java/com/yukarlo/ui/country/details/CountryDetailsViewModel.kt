package com.yukarlo.ui.country.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yukarlo.base.BaseViewModel
import com.yukarlo.common.android.CountryInputModel
import com.yukarlo.coronow.stack.cases.domain.GetCountryDetailsUseCase
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
internal class CountryDetailsViewModel @ViewModelInject constructor(
    private val mGetCountryDetailsUseCase: GetCountryDetailsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<CountryDetailsViewState, CountryDetailsViewEvent, CountryDetailsViewAction>(
    CountryDetailsViewState()
) {

    private val continentIsoArgs =
        savedStateHandle.get<CountryInputModel>("inputModel")?.mCountryIso ?: ""

    init {
        viewModelScope.launch {
            intentChannel.send(CountryDetailsViewAction.LoadDetails)
            handleIntents()
        }
    }

    // region Private Functions

    private suspend fun handleIntents() {
        intentChannel
            .asFlow()
            .collect { action ->
                when (action) {
                    is CountryDetailsViewAction.LoadDetails -> {
                        mGetCountryDetailsUseCase.execute(params = continentIsoArgs)
                            .onStart { sendEvent(CountryDetailsViewEvent.DetailsLoading) }
                            .catch { sendEvent(CountryDetailsViewEvent.DetailsLoadFailure) }
                            .collect {
                                sendEvent(CountryDetailsViewEvent.DetailsLoadSuccess(details = it))
                            }
                    }
                }
            }
    }

    // endregion

    override fun onReduceState(viewEvent: CountryDetailsViewEvent): CountryDetailsViewState =
        when (viewEvent) {
            is CountryDetailsViewEvent.DetailsLoading -> state.copy(
                isLoading = true,
                isError = false
            )
            is CountryDetailsViewEvent.DetailsLoadFailure -> state.copy(
                isLoading = false,
                isError = true
            )
            is CountryDetailsViewEvent.DetailsLoadSuccess -> state.copy(
                isLoading = false,
                isError = false,
                details = viewEvent.details
            )
        }
}
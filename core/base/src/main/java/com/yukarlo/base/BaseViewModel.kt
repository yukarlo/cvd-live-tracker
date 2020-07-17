package com.yukarlo.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.Delegates

abstract class BaseViewModel<ViewState : BaseViewState, ViewEvent : BaseViewEvent>(
    initialSate: ViewState
) : ViewModel() {

    private val updateUiState: MutableStateFlow<ViewState> = MutableStateFlow(initialSate)
    val onUiStateUpdated: StateFlow<ViewState>
        get() = updateUiState

    protected var state by Delegates.observable(initialSate) { _, _, new ->
        updateUiState.value = new
    }

    fun sendEvent(viewEvent: ViewEvent) {
        state = onReduceState(viewEvent)
    }

    protected abstract fun onReduceState(viewEvent: ViewEvent): ViewState
}

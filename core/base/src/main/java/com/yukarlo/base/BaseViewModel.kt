package com.yukarlo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.Delegates

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseViewAction, ViewEvent : BaseViewEvent>(
    initialSate: ViewState
) : ViewModel() {

    private val updateUiState: MutableStateFlow<ViewState> = MutableStateFlow<ViewState>(initialSate)
    val onUiStateUpdated: StateFlow<ViewState>
        get() = updateUiState

    private val updateUiEvent: MutableLiveData<ViewEvent> = MutableLiveData()
    val onUiEventUpdated: LiveData<ViewEvent>
        get() = updateUiEvent

    protected var state by Delegates.observable(initialSate) { _, _, new ->
        updateUiState.value = new
    }

    fun sendEvent(event: ViewEvent) {
        updateUiEvent.postValue(event)
    }

    fun sendAction(viewAction: ViewAction) {
        state = onReduceState(viewAction)
    }

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}

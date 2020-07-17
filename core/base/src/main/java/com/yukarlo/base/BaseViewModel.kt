package com.yukarlo.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.Delegates

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
abstract class BaseViewModel<ViewState : BaseViewState, ViewEvent : BaseViewEvent, ViewAction : BaseViewAction>(
    initialSate: ViewState
) : ViewModel() {

    protected val intentChannel = ConflatedBroadcastChannel<ViewAction>()

    private val updateUiState: MutableStateFlow<ViewState> = MutableStateFlow(initialSate)
    val onUiStateUpdated: StateFlow<ViewState>
        get() = updateUiState

    protected var state by Delegates.observable(initialSate) { _, _, new ->
        updateUiState.value = new
    }

    fun sendAction(viewAction: ViewAction) {
        intentChannel.offer(element = viewAction)
    }

    fun sendEvent(viewEvent: ViewEvent) {
        state = onReduceState(viewEvent)
    }

    protected abstract fun onReduceState(viewEvent: ViewEvent): ViewState
}

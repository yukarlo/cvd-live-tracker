package com.yukarlo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
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

    private val navigate: MutableLiveData<SingleEvent<NavDirections>> =
        MutableLiveData<SingleEvent<NavDirections>>()
    val onNavigate: LiveData<SingleEvent<NavDirections>>
        get() = navigate

    protected var state by Delegates.observable(initialSate) { _, _, new ->
        updateUiState.value = new
    }

    fun sendAction(viewAction: ViewAction) {
        intentChannel.offer(element = viewAction)
    }

    protected fun sendEvent(viewEvent: ViewEvent) {
        state = onReduceState(viewEvent)
    }

    protected fun sendSingleEvent(navDirections: NavDirections) {
        navigate.postValue(SingleEvent(content = navDirections))
    }

    protected abstract fun onReduceState(viewEvent: ViewEvent): ViewState
}

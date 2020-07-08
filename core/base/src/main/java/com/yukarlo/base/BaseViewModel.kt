package com.yukarlo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseViewAction>(initialSate: ViewState) :
    ViewModel() {

    private val stateMutableLiveData: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    val stateLiveData: LiveData<ViewState>
        get() = stateMutableLiveData

    protected var state by Delegates.observable(initialSate) { _, _, new ->
        stateMutableLiveData.postValue(new)
    }

    fun sendAction(viewAction: ViewAction) {
        state = onReduceState(viewAction)
    }

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}

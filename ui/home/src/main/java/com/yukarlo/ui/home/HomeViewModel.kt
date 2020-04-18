package com.yukarlo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.domain.GetCvdCasesSummaryUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val mGetCvdCasesSummaryUseCase: GetCvdCasesSummaryUseCase
) : ViewModel() {

    fun getSummary(): LiveData<CasesSummaryModel> =
        mGetCvdCasesSummaryUseCase.execute()
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
}

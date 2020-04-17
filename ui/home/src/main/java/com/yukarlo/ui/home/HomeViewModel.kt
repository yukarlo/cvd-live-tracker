package com.yukarlo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.lib.cases.domain.GetCvdCasesSummaryUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val mGetCvdCasesSummaryUseCase: GetCvdCasesSummaryUseCase
) : ViewModel() {

    fun getSummary(): LiveData<CasesSummaryResponseModel> =
        mGetCvdCasesSummaryUseCase.execute()
            .asLiveData()
}

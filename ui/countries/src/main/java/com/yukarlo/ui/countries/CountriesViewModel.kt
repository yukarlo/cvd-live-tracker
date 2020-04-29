package com.yukarlo.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yukarlo.lib.cases.domain.GetAllCountriesCasesUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase
) : ViewModel() {

    fun getAll() = mGetAllCountriesCasesUseCase.execute()
        .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
}
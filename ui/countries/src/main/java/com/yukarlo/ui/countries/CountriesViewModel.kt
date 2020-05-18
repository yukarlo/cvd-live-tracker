package com.yukarlo.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.lib.cases.domain.GetAllCountriesCasesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase
) : ViewModel() {

    private var completeCountryList: List<CasesCountriesModel> = emptyList()

    private val updateCountry: MutableLiveData<List<CasesCountriesModel>> = MutableLiveData()
    val onCountryUpdated: LiveData<List<CasesCountriesModel>>
        get() = updateCountry

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mGetAllCountriesCasesUseCase.execute().collect { countryList ->
                onCountryUpdate(countryList = countryList)
            }
        }
    }

    private fun onCountryUpdate(countryList: List<CasesCountriesModel>) {
        completeCountryList = countryList
        updateCountry.postValue(countryList)
    }

    fun filterCountry(filter: String) {
        val filteredCountry = completeCountryList.filter {
            val countryName = it.countryName.toLowerCase(Locale.getDefault())
            val countryIso = it.countryIso.toLowerCase(Locale.getDefault())
            countryName.contains(filter.toLowerCase(Locale.getDefault())) || countryIso.contains(
                filter.toLowerCase(Locale.getDefault())
            )
        }
        updateCountry.postValue(filteredCountry)
    }
}

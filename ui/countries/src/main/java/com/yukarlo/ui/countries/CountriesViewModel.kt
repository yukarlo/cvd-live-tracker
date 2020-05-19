package com.yukarlo.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.common.android.CountriesInputModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.coronow.stack.cases.domain.GetAllCountriesCasesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val mInputModel: CountriesInputModel,
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase
) : ViewModel() {

    private var completeCountryList: List<CasesCountriesModel> = emptyList()

    private val continentName: MutableLiveData<String> = MutableLiveData()
    val onContinentNameUpdated: LiveData<String>
        get() = continentName

    private val updateCountry: MutableLiveData<List<CasesCountriesModel>> = MutableLiveData()
    val onCountryUpdated: LiveData<List<CasesCountriesModel>>
        get() = updateCountry

    init {
        if (mInputModel.mContinentName.isNotEmpty()) {
            continentName.postValue(mInputModel.mContinentName)
        }

        viewModelScope.launch(Dispatchers.IO) {
            mGetAllCountriesCasesUseCase.execute().collect { countryList ->
                completeCountryList = countryList
                onCountryUpdate(countryList = filterContinent(countryList = countryList))
            }
        }
    }

    fun filterCountry(filter: String) {
        val filteredCountryList = completeCountryList.filter {
            val countryName = it.countryName.toLowerCase(Locale.getDefault())
            val countryIso = it.countryIso.toLowerCase(Locale.getDefault())
            countryName.contains(filter.toLowerCase(Locale.getDefault())) || countryIso.contains(
                filter.toLowerCase(Locale.getDefault())
            )
        }
        onCountryUpdate(countryList = filterContinent(countryList = filteredCountryList))
    }

    fun sortCountry(sortBy: SortBy) {
        val sortedCountryList = when (sortBy) {
            SortBy.Confirmed -> {
                completeCountryList.sortedByDescending {
                    it.totalCasesCount
                }
            }
            SortBy.Deceased -> {
                completeCountryList.sortedByDescending {
                    it.totalDeceasedCount
                }
            }
            SortBy.Recovered -> {
                completeCountryList.sortedByDescending {
                    it.totalRecoveredCount
                }
            }
            else -> {
                completeCountryList.sortedBy {
                    it.countryName
                }
            }
        }

        onCountryUpdate(countryList = filterContinent(countryList = sortedCountryList))
    }

    private fun onCountryUpdate(countryList: List<CasesCountriesModel>) {
        updateCountry.postValue(countryList)
    }

    private fun filterContinent(countryList: List<CasesCountriesModel>): List<CasesCountriesModel> =
        if (mInputModel.mContinentName.isNotEmpty()) {
            countryList.filter {
                it.continent == mInputModel.mContinentName
            }
        } else {
            countryList
        }
}

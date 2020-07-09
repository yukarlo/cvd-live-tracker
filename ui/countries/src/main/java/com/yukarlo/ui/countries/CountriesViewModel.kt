package com.yukarlo.ui.countries

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yukarlo.base.BaseViewModel
import com.yukarlo.common.android.CountriesInputModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.coronow.stack.cases.domain.GetAllCountriesCasesUseCase
import com.yukarlo.ui.countries.CountriesViewAction.CountriesLoadSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

internal class CountriesViewModel @ViewModelInject constructor(
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<CountriesViewState, CountriesViewAction>(CountriesViewState()) {

    private val continentNameArgs =
        savedStateHandle.get<CountriesInputModel>("continent")?.mContinentName ?: ""

    private var completeCountryList: List<CasesCountriesModel> = emptyList()

    private val continentName: MutableLiveData<String> = MutableLiveData()
    val onContinentNameUpdated: LiveData<String>
        get() = continentName

    init {
        if (continentNameArgs.isNotEmpty()) {
            continentName.postValue(continentNameArgs)
        }

        viewModelScope.launch(Dispatchers.IO) {
            sendAction(CountriesViewAction.CountriesLoading)
            mGetAllCountriesCasesUseCase.execute(params = Unit)
                .collect { countryList ->
                    completeCountryList = countryList
                    sendAction(CountriesLoadSuccess(countries = countryList))
                }
        }
    }


    override fun onReduceState(viewAction: CountriesViewAction): CountriesViewState =
        when (viewAction) {
            is CountriesViewAction.CountriesLoading -> state.copy()
            is CountriesLoadSuccess -> state.copy(
                isLoading = false,
                isError = false,
                countries = viewAction.countries
            )
            is CountriesViewAction.CountriesLoadFailure -> state.copy(
                isLoading = false,
                isError = true,
                countries = listOf()
            )
        }


    fun filterCountry(filter: String) {
        val filteredCountryList = completeCountryList.filter {
            val countryName = it.countryName.toLowerCase(Locale.getDefault())
            val countryIso = it.countryIso.toLowerCase(Locale.getDefault())
            countryName.contains(filter.toLowerCase(Locale.getDefault())) || countryIso.contains(
                filter.toLowerCase(Locale.getDefault())
            )
        }
        sendAction(CountriesLoadSuccess(countries = filterContinent(countryList = filteredCountryList)))
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
        sendAction(CountriesLoadSuccess(countries = filterContinent(countryList = sortedCountryList)))
    }

    private fun filterContinent(countryList: List<CasesCountriesModel>): List<CasesCountriesModel> =
        if (continentNameArgs.isNotEmpty()) {
            countryList.filter {
                it.continent == continentNameArgs
            }
        } else {
            countryList
        }
}

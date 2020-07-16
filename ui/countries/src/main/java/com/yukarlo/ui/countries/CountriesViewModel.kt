package com.yukarlo.ui.countries

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
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
) : BaseViewModel<CountriesViewState, CountriesViewAction, CountriesViewEvent>(CountriesViewState()) {

    private val continentNameArgs =
        savedStateHandle.get<CountriesInputModel>("continent")?.mContinentName ?: ""

    private var completeCountryList: List<CasesCountriesModel> = emptyList()

    init {
        if (continentNameArgs.isNotEmpty()) {
            sendEvent(event = CountriesViewEvent.ContinentName(continentName = continentNameArgs))
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
        val sortedCountryList =
            when (sortBy) {
                SortBy.Country -> {
                    completeCountryList.sortedBy { it.countryName }
                }
                else -> {
                    completeCountryList.sortedByDescending {
                        when (sortBy) {
                            SortBy.Confirmed -> it.totalCasesCount
                            SortBy.Deceased -> it.totalDeceasedCount
                            SortBy.Recovered -> it.totalRecoveredCount
                            else -> it.totalActiveCount
                        }
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

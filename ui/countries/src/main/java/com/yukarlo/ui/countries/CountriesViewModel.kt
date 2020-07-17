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
import com.yukarlo.ui.countries.CountriesViewAction.CountriesLoadFailure
import com.yukarlo.ui.countries.CountriesViewAction.CountriesLoadSuccess
import com.yukarlo.ui.countries.CountriesViewAction.CountriesLoading
import com.yukarlo.ui.countries.CountriesViewAction.CountriesSortedBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
internal class CountriesViewModel @ViewModelInject constructor(
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<CountriesViewState, CountriesViewAction>(CountriesViewState()) {

    val intentChannel = Channel<CountriesViewEvent>(Channel.UNLIMITED)

    private val continentNameArgs =
        savedStateHandle.get<CountriesInputModel>("continent")?.mContinentName ?: ""

    private lateinit var completeCountryList: List<CasesCountriesModel>

    private val continentName: MutableLiveData<String> = MutableLiveData()
    val onContinentNameUpdated: LiveData<String>
        get() = continentName

    init {
        if (continentNameArgs.isNotEmpty()) {
            continentName.postValue(continentNameArgs)
        }

        viewModelScope.launch {
            intentChannel.send(CountriesViewEvent.RefreshData)
            handleIntents()
        }
    }

    override fun onReduceState(viewAction: CountriesViewAction): CountriesViewState =
        when (viewAction) {
            is CountriesLoading -> state.copy()
            is CountriesLoadSuccess -> state.copy(
                isLoading = false,
                isError = false,
                countries = viewAction.countries
            )
            is CountriesLoadFailure -> state.copy(
                isLoading = false,
                isError = true,
                countries = listOf()
            )
            is CountriesSortedBy -> state.copy(
                sortBy = viewAction.sortedBy
            )
        }

    // region Private Functions

    private suspend fun handleIntents() {
        intentChannel
            .consumeAsFlow()
            .collect { intent ->
                when (intent) {
                    is CountriesViewEvent.RefreshData -> refreshData()
                    is CountriesViewEvent.SortCountriesBy -> sortCountry(sortBy = intent.sortBy)
                    is CountriesViewEvent.FilterCountries -> filterCountry(query = intent.query)
                }
            }
    }

    private fun filterCountry(query: String) {
        val filteredCountryList = completeCountryList.filter {
            val countryName = it.countryName.toLowerCase(Locale.getDefault())
            val countryIso = it.countryIso.toLowerCase(Locale.getDefault())
            countryName.contains(query.toLowerCase(Locale.getDefault())) || countryIso.contains(
                query.toLowerCase(Locale.getDefault())
            )
        }
        sendAction(CountriesLoadSuccess(countries = filterContinent(countryList = filteredCountryList)))
    }

    private fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mGetAllCountriesCasesUseCase.execute(params = Unit)
                    .onStart { sendAction(CountriesLoading) }
                    .collect { countryList ->
                        completeCountryList = countryList
                        sortCountry(sortBy = SortBy.Country)
                    }
            } catch (e: Exception) {
                sendAction(CountriesLoadFailure)
            }
        }
    }

    private fun sortCountry(sortBy: SortBy) {
        sendAction(CountriesSortedBy(sortedBy = sortBy))

        completeCountryList = when (sortBy) {
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

        sendAction(CountriesLoadSuccess(countries = filterContinent(countryList = completeCountryList)))
    }

    private fun filterContinent(countryList: List<CasesCountriesModel>): List<CasesCountriesModel> =
        if (continentNameArgs.isNotEmpty()) {
            countryList.filter {
                it.continent == continentNameArgs
            }
        } else {
            countryList
        }

    // endregion
}

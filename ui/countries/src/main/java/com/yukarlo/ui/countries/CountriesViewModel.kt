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
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesLoadFailure
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesLoadSuccess
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesLoading
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesSortedBy
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
) : BaseViewModel<CountriesViewState, CountriesViewEvent>(CountriesViewState()) {

    val intentChannel = Channel<CountriesViewAction>(Channel.UNLIMITED)

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
            intentChannel.send(CountriesViewAction.InitialLoad)
            handleIntents()
        }
    }

    override fun onReduceState(viewEvent: CountriesViewEvent): CountriesViewState =
        when (viewEvent) {
            is CountriesLoading -> state.copy()
            is CountriesLoadSuccess -> state.copy(
                isLoading = false,
                isError = false,
                countries = viewEvent.countries
            )
            is CountriesLoadFailure -> state.copy(
                isLoading = false,
                isError = true,
                countries = listOf()
            )
            is CountriesSortedBy -> state.copy(
                sortBy = viewEvent.sortedBy
            )
        }

    // region Private Functions

    private suspend fun handleIntents() {
        intentChannel
            .consumeAsFlow()
            .collect { action ->
                when (action) {
                    is CountriesViewAction.InitialLoad,
                    is CountriesViewAction.RefreshData -> loadData()
                    is CountriesViewAction.SortCountriesBy -> sortCountry(sortBy = action.sortBy)
                    is CountriesViewAction.FilterCountries -> filterCountry(query = action.query)
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
        sendEvent(CountriesLoadSuccess(countries = filterContinent(countryList = filteredCountryList)))
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mGetAllCountriesCasesUseCase.execute(params = Unit)
                    .onStart { sendEvent(CountriesLoading) }
                    .collect { countryList ->
                        completeCountryList = countryList
                        sortCountry(sortBy = SortBy.Country)
                    }
            } catch (e: Exception) {
                sendEvent(CountriesLoadFailure)
            }
        }
    }

    private fun sortCountry(sortBy: SortBy) {
        sendEvent(CountriesSortedBy(sortedBy = sortBy))

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

        sendEvent(CountriesLoadSuccess(countries = filterContinent(countryList = completeCountryList)))
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

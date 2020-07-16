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
) : BaseViewModel<CountriesViewState, CountriesViewAction, CountriesViewEvent>(CountriesViewState()) {

    val intentChannel = Channel<CountriesViewEvent>(Channel.UNLIMITED)

    private val continentNameArgs =
        savedStateHandle.get<CountriesInputModel>("continent")?.mContinentName ?: ""

    private lateinit var completeCountryList: List<CasesCountriesModel>

    init {
        viewModelScope.launch {
            if (continentNameArgs.isNotEmpty()) {
                intentChannel.send(CountriesViewEvent.ContinentName(continentName = continentNameArgs))
            }
            intentChannel.send(CountriesViewEvent.RefreshData)

            handleIntents()
        }
    }

    private suspend fun handleIntents() {
        intentChannel
            .consumeAsFlow()
            .collect { intent ->
                when (intent) {
                    is CountriesViewEvent.RefreshData -> {
                        mGetAllCountriesCasesUseCase.execute(params = Unit)
                            .onStart { sendAction(CountriesViewAction.CountriesLoading) }
                            .collect { countryList ->
                                completeCountryList = countryList
                                sortCountry(sortBy = SortBy.Country)
                            }
                    }
                    is CountriesViewEvent.SortedBy -> {
                        sortCountry(sortBy = intent.sortBy)
                    }
                    is CountriesViewEvent.ContinentName -> {
                        sendEvent(event = CountriesViewEvent.ContinentName(continentName = intent.continentName))
                    }
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

    private fun sortCountry(sortBy: SortBy) {
        sendEvent(event = CountriesViewEvent.SortedBy(sortBy = sortBy))

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
}

package com.yukarlo.ui.countries

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yukarlo.base.BaseViewModel
import com.yukarlo.common.android.CountriesInputModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.SortBy
import com.yukarlo.coronow.stack.cases.domain.AddToFavoriteUseCase
import com.yukarlo.coronow.stack.cases.domain.GetAllCountriesCasesUseCase
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesLoadFailure
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesLoadSuccess
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesLoading
import com.yukarlo.ui.countries.CountriesViewEvent.CountriesSortedBy
import com.yukarlo.ui.countries.model.CountriesUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
internal class CountriesViewModel @ViewModelInject constructor(
    private val mAddToFavoriteUseCase: AddToFavoriteUseCase,
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<CountriesViewState, CountriesViewEvent, CountriesViewAction, CountriesViewSideEffect>(
    CountriesViewState()
) {

    private lateinit var completeCountryList: List<CasesCountriesModel>
    private val uiModel: CountriesUiModel = CountriesUiModel()

    private val continentNameArgs =
        savedStateHandle.get<CountriesInputModel>("inputModel")?.mContinentName ?: ""

    init {
        continentNameArgs
            .takeIf {
                it.isNotEmpty()
            }
            ?.let {
                sendSideEffect(CountriesViewSideEffect.UpdateCountryName(name = it))
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
            .asFlow()
            .collect { action ->
                when (action) {
                    is CountriesViewAction.InitialLoad,
                    is CountriesViewAction.RefreshData -> loadCountries()
                    is CountriesViewAction.SortCountriesBy -> sortCountry(sortBy = action.sortBy)
                    is CountriesViewAction.FilterCountries -> filterCountry(query = action.query)
                    is CountriesViewAction.AddToFavorite -> {
                        mAddToFavoriteUseCase.run(params = action.country)
                        loadCountries()
                    }
                    is CountriesViewAction.Navigate -> sendSideEffect(
                        CountriesViewSideEffect.NavigateTo(
                            action.directions
                        )
                    )
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

    private fun loadCountries(sortBy: SortBy? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            mGetAllCountriesCasesUseCase.execute(params = sortBy ?: uiModel.sortBy)
                .onStart { sendEvent(CountriesLoading) }
                .catch { sendEvent(CountriesLoadFailure) }
                .collect { countryList ->
                    completeCountryList = countryList
                    sendEvent(CountriesLoadSuccess(countries = filterContinent(countryList = countryList)))
                }
        }
    }

    private fun sortCountry(sortBy: SortBy) {
        uiModel.sortBy = sortBy
        sendEvent(CountriesSortedBy(sortedBy = sortBy))
        loadCountries(sortBy = sortBy)
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

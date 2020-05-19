package com.yukarlo.ui.continents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.common.android.ContinentInputModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.coronow.stack.cases.domain.GetAllCountriesCasesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContinentsViewModel @Inject constructor(
    private val mInputModel: ContinentInputModel,
    private val mGetAllCountriesCasesUseCase: GetAllCountriesCasesUseCase
) : ViewModel() {

    private val continentName: MutableLiveData<String> = MutableLiveData()
    val onContinentNameUpdated: LiveData<String>
        get() = continentName

    private val updateCountry: MutableLiveData<List<CasesCountriesModel>> = MutableLiveData()
    val onCountryUpdated: LiveData<List<CasesCountriesModel>>
        get() = updateCountry

    init {
        viewModelScope.launch(Dispatchers.IO) {
            continentName.postValue(mInputModel.mContinentName)

            mGetAllCountriesCasesUseCase.execute().collect { countryList ->
                countryList.filter {
                    it.continent == mInputModel.mContinentName
                }.run {
                    onCountryUpdate(countryList = this)
                }
            }
        }
    }

    private fun onCountryUpdate(countryList: List<CasesCountriesModel>) {
        updateCountry.postValue(countryList)
    }
}

package com.yukarlo.ui.country.details

import com.yukarlo.base.BaseViewEvent
import com.yukarlo.core.domain.model.CasesCountriesModel

internal sealed class CountryDetailsViewEvent : BaseViewEvent {
    object DetailsLoading : CountryDetailsViewEvent()
    class DetailsLoadSuccess(val details: CasesCountriesModel) : CountryDetailsViewEvent()
    object DetailsLoadFailure : CountryDetailsViewEvent()
}

package com.yukarlo.ui.country.details

import com.yukarlo.base.BaseViewAction

internal sealed class CountryDetailsViewAction :BaseViewAction {
    object LoadDetails: CountryDetailsViewAction()
}

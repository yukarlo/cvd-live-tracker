package com.yukarlo.ui.country.details.compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.yukarlo.core.domain.model.CasesCountriesModel

@Composable
fun countryDetails(details: CasesCountriesModel) {
    Column {
        Text(text = details.countryName)
        Text(text = details.totalActiveCount.toString())
    }
}

@Preview(showBackground = true, name = "Country's Details")
@Composable
private fun defaultPreview() {
    countryDetails(
        CasesCountriesModel(
            countryName = "Compose Country",
            totalActiveCount = 600
        )
    )
}
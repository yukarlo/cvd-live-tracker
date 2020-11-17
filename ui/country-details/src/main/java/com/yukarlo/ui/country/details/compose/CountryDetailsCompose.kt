package com.yukarlo.ui.country.details.compose

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.yukarlo.common.android.compose.components.CircularImage
import com.yukarlo.common.android.compose.components.casesCard
import com.yukarlo.common.android.compose.theme.CoronowTheme
import com.yukarlo.core.domain.model.CasesCountriesModel

@Composable
fun countryDetailsLayout(details: CasesCountriesModel) {
    ConstraintLayout(
        constraintSet = decoupledConstraints(),
        modifier = Modifier
            .padding(10.dp)
    ) {
        CircularImage(
            imageUrl = details.countryFlag,
            modifier = Modifier.layoutId("countryFlag")
                .preferredSize(32.dp)
        )
        Text(
            color = CoronowTheme.colors.textPrimary,
            text = "${details.countryName} - Covid19 Cases",
            fontSize = 28.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.layoutId("countryName")
                .padding(start = 5.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        )
        Text(
            color = CoronowTheme.colors.textPrimary,
            text = "Confirmed Cases: ${details.totalCasesCount}",
            fontSize = 16.sp,
            modifier = Modifier.layoutId("confirmedCases")
        )
        Text(
            color = CoronowTheme.colors.textPrimary,
            text = "Active Cases: ${details.totalActiveCount}",
            fontSize = 16.sp,
            modifier = Modifier.layoutId("activeCount")
        )
        casesCard(
            text = "Critical",
            count = details.critical.toString(),
            backgroundColor = CoronowTheme.colors.cardCriticalBackground,
            cardModifier = Modifier
                .layoutId("criticalCard")
                .wrapContentHeight()
                .padding(start = 0.dp, top = 10.dp, end = 5.dp, bottom = 0.dp)
        )
        casesCard(
            text = "Recovered",
            count = details.totalRecoveredCount.toString(),
            backgroundColor = CoronowTheme.colors.cardRecoveredBackground,
            cardModifier = Modifier
                .layoutId("recoveredCard")
                .wrapContentHeight()
                .padding(start = 5.dp, top = 10.dp, end = 0.dp, bottom = 0.dp)
        )
        casesCard(
            text = "Deceased",
            count = details.totalDeceasedCount.toString(),
            backgroundColor = CoronowTheme.colors.cardDeceasedBackground,
            cardModifier = Modifier
                .layoutId("deceasedCard")
                .wrapContentHeight()
                .padding(start = 0.dp, top = 10.dp, end = 5.dp, bottom = 0.dp)
        )
        casesCard(
            text = "Test Conducted",
            count = details.tests.toString(),
            backgroundColor = CoronowTheme.colors.cardAdditionalInformationBackground,
            cardModifier = Modifier
                .layoutId("testConductedCard")
                .wrapContentHeight()
                .padding(start = 5.dp, top = 10.dp, end = 0.dp, bottom = 0.dp)
        )
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val countryFlag = createRefFor(id = "countryFlag")
        val countryName = createRefFor(id = "countryName")
        val confirmedCases = createRefFor(id ="confirmedCases")
        val activeCount = createRefFor(id ="activeCount")
        val criticalCard = createRefFor(id ="criticalCard")
        val recoveredCard = createRefFor(id ="recoveredCard")
        val deceasedCard = createRefFor(id ="deceasedCard")
        val testConductedCard = createRefFor(id ="testConductedCard")

        constrain(countryFlag) {
            top.linkTo(anchor = countryName.top)
            end.linkTo(anchor = countryName.start)
            start.linkTo(anchor = parent.start)
            bottom.linkTo(anchor = countryName.bottom)
        }
        constrain(countryName) {
            top.linkTo(anchor = parent.top)
            start.linkTo(anchor = countryFlag.end)
            end.linkTo(anchor = parent.end)
            bottom.linkTo(anchor = confirmedCases.top)
            width = Dimension.fillToConstraints
        }
        constrain(confirmedCases) {
            top.linkTo(anchor = countryName.bottom)
        }
        constrain(activeCount) {
            top.linkTo(anchor = confirmedCases.bottom)
        }
        constrain(criticalCard) {
            top.linkTo(anchor = activeCount.bottom)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = recoveredCard.start)
            width = Dimension.fillToConstraints
        }
        constrain(recoveredCard) {
            top.linkTo(anchor = activeCount.bottom)
            start.linkTo(anchor = criticalCard.end)
            end.linkTo(anchor = parent.end)
            width = Dimension.fillToConstraints
        }
        constrain(deceasedCard) {
            top.linkTo(anchor = recoveredCard.bottom)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = testConductedCard.start)
            width = Dimension.fillToConstraints
        }
        constrain(testConductedCard) {
            top.linkTo(anchor = recoveredCard.bottom)
            start.linkTo(anchor = deceasedCard.end)
            end.linkTo(anchor = parent.end)
            width = Dimension.fillToConstraints
        }
    }
}

@Preview(showBackground = true, name = "Country's Details Light Layout")
@Composable
private fun CountryDetailsLightLayoutPreview() {
    CoronowTheme(darkTheme = false) {
        countryDetailsLayout(
            CasesCountriesModel(
                countryName = "Philippines",
                totalActiveCount = 6542000,
                totalDeceasedCount = 1231250,
                totalRecoveredCount = 4314300,
                totalCasesCount = 16951550,
                critical = 1990100,
                tests = 8813640
            )
        )
    }
}

@Preview(showBackground = false, name = "Country's Details Dark Layout")
@Composable
private fun CountryDetailsDarkLayoutPreview() {
    CoronowTheme(darkTheme = true) {
        countryDetailsLayout(
            CasesCountriesModel(
                countryName = "Philippines",
                totalActiveCount = 6542000,
                totalDeceasedCount = 1231250,
                totalRecoveredCount = 4314300,
                totalCasesCount = 16951550,
                critical = 1990100,
                tests = 8813640
            )
        )
    }
}
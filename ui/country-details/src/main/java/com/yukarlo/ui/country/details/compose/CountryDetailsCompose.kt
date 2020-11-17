package com.yukarlo.ui.country.details.compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.ui.country.details.compose.ui.cyan300
import com.yukarlo.ui.country.details.compose.ui.gray00
import com.yukarlo.ui.country.details.compose.ui.green300
import com.yukarlo.ui.country.details.compose.ui.red300
import com.yukarlo.ui.country.details.compose.ui.yellow300

@ExperimentalLayout
@Composable
fun countryDetailsConstraintLayout(details: CasesCountriesModel) {
    ConstraintLayout(
        constraintSet = decoupledConstraints(),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = "${details.countryName} - Covid19 Cases",
            fontSize = 28.sp,
            modifier = Modifier.layoutId("countryName")
        )
        Text(
            text = "Confirmed Cases: ${details.totalCasesCount}",
            fontSize = 16.sp,
            modifier = Modifier.layoutId("confirmedCases")
        )
        Text(
            text = "Active Cases: ${details.totalActiveCount}",
            fontSize = 16.sp,
            modifier = Modifier.layoutId("activeCount")
        )
        ownCard(
            text = "Critical",
            count = details.critical.toString(),
            backgroundColor = yellow300,
            cardModifier = Modifier
                .layoutId("criticalCard")
                .wrapContentHeight()
                .padding(start = 0.dp, top = 0.dp, end = 5.dp, bottom = 0.dp),
        )
        ownCard(
            text = "Recovered",
            count = details.totalRecoveredCount.toString(),
            backgroundColor = green300,
            cardModifier = Modifier
                .layoutId("recoveredCard")
                .wrapContentHeight()
                .padding(start = 5.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        )
        ownCard(
            text = "Deceased",
            count = details.totalDeceasedCount.toString(),
            backgroundColor = red300,
            cardModifier = Modifier
                .layoutId("deceasedCard")
                .wrapContentHeight()
                .padding(start = 0.dp, top = 10.dp, end = 5.dp, bottom = 0.dp)
        )
        ownCard(
            text = "Test Conducted",
            count = details.tests.toString(),
            backgroundColor = cyan300,
            cardModifier = Modifier
                .layoutId("testConductedCard")
                .wrapContentHeight()
                .padding(start = 5.dp, top = 10.dp, end = 0.dp, bottom = 0.dp)
        )
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val countryName = createRefFor(id = "countryName")
        val confirmedCases = createRefFor(id ="confirmedCases")
        val activeCount = createRefFor(id ="activeCount")
        val criticalCard = createRefFor(id ="criticalCard")
        val recoveredCard = createRefFor(id ="recoveredCard")
        val deceasedCard = createRefFor(id ="deceasedCard")
        val testConductedCard = createRefFor(id ="testConductedCard")

        constrain(countryName) {
            top.linkTo(anchor = parent.top)
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


@Composable
private fun ownCard(
    text: String,
    count: String,
    backgroundColor: Color,
    cardModifier: Modifier
) {
    Card(
        backgroundColor = backgroundColor,
        contentColor = gray00,
        modifier = cardModifier,
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                top = 40.dp,
                bottom = 40.dp
            )
        ) {
            val (
                textLabel,
                textCount
            ) = createRefs()

            Text(
                text = count,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(ref = textCount) {
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = textLabel.top)
                }
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(ref = textLabel) {
                    top.linkTo(anchor = textCount.bottom)
                    start.linkTo(anchor = textCount.start)
                    end.linkTo(anchor = textCount.end)
                    bottom.linkTo(anchor = parent.bottom)
                }
            )
        }
    }
}

@ExperimentalLayout
@Preview(showBackground = true, name = "Country's Details Constraint Layout")
@Composable
private fun defaultCountryDetailsConstraintLayoutPreview() {
    countryDetailsConstraintLayout(
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
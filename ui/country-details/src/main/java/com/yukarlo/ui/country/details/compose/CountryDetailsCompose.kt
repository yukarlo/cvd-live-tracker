package com.yukarlo.ui.country.details.compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.ui.country.details.compose.ui.cyan300
import com.yukarlo.ui.country.details.compose.ui.gray00
import com.yukarlo.ui.country.details.compose.ui.yellow300

@ExperimentalLayout
@Composable
fun countryDetailsConstraintLayout(details: CasesCountriesModel) {
    ConstraintLayout(
        modifier = Modifier
            .padding(10.dp)
    ) {
        val (
            countryName,
            confirmedCases,
            activeCount,
            criticalCard,
            criticalLabel,
            criticalCount,
            recoveredCard,
            recoveredLabel,
            recoveredCount
        ) = createRefs()

        Text(
            text = "${details.countryName} - Covid19 Cases",
            fontSize = 28.sp,
            modifier = Modifier.constrainAs(ref = countryName) {
                top.linkTo(anchor = parent.top)
            }
        )
        Text(
            text = "Confirmed Cases: ${details.totalCasesCount}",
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(ref = confirmedCases) {
                top.linkTo(anchor = countryName.bottom)
            }
        )
        Text(
            text = "Active Cases: ${details.totalActiveCount}",
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(ref = activeCount) {
                top.linkTo(anchor = confirmedCases.bottom)
            }
        )
        Card(
            backgroundColor = yellow300,
            contentColor = gray00,
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(ref = criticalCard) {
                    top.linkTo(anchor = activeCount.bottom)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = recoveredCard.start)
                }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 40.dp,
                        bottom = 40.dp
                    )
            ) {
                Text(
                    text = details.critical.toString(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(ref = criticalCount) {
                        top.linkTo(anchor = parent.top)
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = parent.end)
                        bottom.linkTo(anchor = criticalLabel.top)
                    }
                )
                Text(
                    text = "Critical",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(ref = criticalLabel) {
                        top.linkTo(anchor = criticalCount.bottom)
                        start.linkTo(anchor = criticalCount.start)
                        end.linkTo(anchor = criticalCount.end)
                        bottom.linkTo(anchor = parent.bottom)
                    }
                )
            }
        }
        Card(
            backgroundColor = cyan300,
            contentColor = gray00,
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(ref = recoveredCard) {
                    top.linkTo(anchor = activeCount.bottom)
                    start.linkTo(anchor = criticalCard.end)
                    end.linkTo(anchor = parent.end)
                }
        ) {
            ConstraintLayout(
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 40.dp,
                    bottom = 40.dp
                )
            ) {
                Text(
                    text = details.totalRecoveredCount.toString(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(recoveredCount) {
                        top.linkTo(anchor = parent.top)
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = parent.end)
                        bottom.linkTo(recoveredLabel.top)
                    }
                )
                Text(
                    text = "Recovered",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.constrainAs(ref = recoveredLabel) {
                        top.linkTo(anchor = recoveredCount.bottom)
                        start.linkTo(anchor = recoveredCount.start)
                        end.linkTo(anchor = recoveredCount.end)
                        bottom.linkTo(anchor = parent.bottom)
                    }
                )
            }
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
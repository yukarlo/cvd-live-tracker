package com.yukarlo.coronow.stack.local.repository

import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.core.domain.model.FavoriteCountry
import com.yukarlo.core.domain.model.SortBy
import com.yukarlo.coronow.cvdDatabase
import java.util.*
import javax.inject.Inject
import kotlin.time.milliseconds

@OptIn(kotlin.time.ExperimentalTime::class)
class CvdCasesLocalRepository @Inject constructor(
    private val database: cvdDatabase
) : ICvdCasesLocalRepository {

    override suspend fun addOrUpdateCountries(countries: List<CasesCountriesModel>) {
        val countriesQueries = database.cvdCountriesCasesQueries
        if (countriesQueries.selectAllCountriesSortedByCountryName().executeAsList().isEmpty()) {
            countries.map {
                countriesQueries.insertCountry(
                    country_name = it.countryName,
                    country_flag = it.countryFlag,
                    country_iso = it.countryIso,
                    continent_name = it.continent,
                    total_cases = it.totalCasesCount,
                    total_today_cases = it.totalTodayCases,
                    total_deaths = it.totalDeceasedCount,
                    total_today_deaths = it.totalTodayDeceased,
                    total_active = it.totalActiveCount,
                    total_recovered = it.totalRecoveredCount,
                    is_favorite = false,
                    time_added = System.currentTimeMillis(),
                    total_critical = it.critical,
                    total_tests = it.tests,
                    updated = it.updatedSince
                )
            }
        } else {
            countries.map {
                countriesQueries.updateCountries(
                    total_cases = it.totalCasesCount,
                    total_today_cases = it.totalTodayCases,
                    total_deaths = it.totalDeceasedCount,
                    total_today_deaths = it.totalTodayDeceased,
                    total_active = it.totalActiveCount,
                    total_recovered = it.totalRecoveredCount,
                    time_added = System.currentTimeMillis(),
                    country_iso = it.countryIso,
                    total_critical = it.critical,
                    total_tests = it.tests,
                    updated = it.updatedSince
                )
            }
        }
    }

    override suspend fun markCountryAsFavorite(country: FavoriteCountry) {
        val countriesQueries = database.cvdCountriesCasesQueries
        countriesQueries.markAsFavorite(
            is_favorite = country.addToFavorite,
            country_iso = country.countryIso
        )
    }

    override suspend fun addContinents(continents: List<CasesContinentsModel>) {
        val continentsQueries = database.cvdContinentsCasesQueries
        continentsQueries.deleteAllContinents()
        continents.map {
            continentsQueries.insertContinent(
                continent_name = it.continentName,
                total_cases = it.totalCasesCount,
                total_recovered = it.totalRecoveredCount,
                total_deaths = it.totalDeceasedCount,
                total_active = it.totalActiveCount,
                total_critical = it.totalCriticalCount
            )
        }
    }

    override suspend fun addSummary(casesSummary: CasesSummaryModel) {
        val summaryQueries = database.cvdSummaryCasesQueries
        summaryQueries.deleteAllSummary()
        with(casesSummary) {
            summaryQueries.insertSummary(
                updated = updatedSince,
                total_cases = totalCasesCount,
                total_deaths = totalDeceasedCount,
                total_recovered = totalRecoveredCount,
                affected_countries = affectedCountries
            )
        }
    }

    override fun getCountries(sortBy: SortBy): List<CasesCountriesModel> =
        with(database.cvdCountriesCasesQueries) {
            val countries = when (sortBy) {
                SortBy.Confirmed -> selectAllCountriesSortedByCases().executeAsList()
                SortBy.Deceased -> selectAllCountriesSortedByDeaths().executeAsList()
                SortBy.Recovered -> selectAllCountriesSortedByRecovered().executeAsList()
                SortBy.Active -> selectAllCountriesSortedByActive().executeAsList()
                else -> selectAllCountriesSortedByCountryName().executeAsList()
            }

            countries
                .takeIf { it.isNotEmpty() }
                ?.takeIf {
                    it.first().time_added.getRelativeTimeInMinutes() < 10 &&
                            it.first().time_added != 0L
                }
                ?.let {
                    countries.map {
                        CasesCountriesModel(
                            countryName = it.country_name,
                            countryIso = it.country_iso,
                            countryFlag = it.country_flag,
                            continent = it.continent_name,
                            totalCasesCount = it.total_cases,
                            totalTodayCases = it.total_today_cases,
                            totalDeceasedCount = it.total_deaths,
                            totalTodayDeceased = it.total_today_deaths,
                            totalRecoveredCount = it.total_recovered,
                            totalActiveCount = it.total_active,
                            isFavorite = it.is_favorite,
                            tests = it.total_tests,
                            critical = it.total_critical,
                            updatedSince = it.updated
                        )
                    }
                } ?: listOf()
        }

    override fun getContinents(): List<CasesContinentsModel> {
        val continentsQueries = database.cvdContinentsCasesQueries
        val continents = continentsQueries.selectAllContinents().executeAsList()

        return continents.map {
            CasesContinentsModel(
                continentName = it.continent_name,
                totalCasesCount = it.total_cases,
                totalDeceasedCount = it.total_deaths,
                totalRecoveredCount = it.total_recovered,
                totalActiveCount = it.total_active,
                totalCriticalCount = it.total_critical
            )
        }
    }

    override fun getSummary(): CasesSummaryModel? {
        val summaryQueries = database.cvdSummaryCasesQueries
        return summaryQueries.selectAllSummary().executeAsOneOrNull()?.run {
            if (updated.getRelativeTimeInMinutes() < 10) {
                CasesSummaryModel(
                    updatedSince = updated,
                    affectedCountries = affected_countries,
                    totalCasesCount = total_cases,
                    totalDeceasedCount = total_deaths,
                    totalRecoveredCount = total_recovered
                )
            } else {
                null
            }
        }
    }

    override fun getCountry(countryIso: String): CasesCountriesModel {
        val countryQueries = database.cvdCountriesCasesQueries

        return countryQueries.selectACountry(country_iso = countryIso).executeAsOne().run {
            CasesCountriesModel(
                countryName = country_name,
                countryIso = country_iso,
                countryFlag = country_flag,
                continent = continent_name,
                totalCasesCount = total_cases,
                totalTodayCases = total_today_cases,
                totalDeceasedCount = total_deaths,
                totalTodayDeceased = total_today_deaths,
                totalRecoveredCount = total_recovered,
                totalActiveCount = total_active,
                isFavorite = is_favorite,
                tests = total_tests,
                critical = total_critical,
                updatedSince = updated
            )
        }
    }

    private fun Long.getRelativeTimeInMinutes(): Int =
        ((Date(System.currentTimeMillis()).time - Date(this).time)
            .milliseconds.inMinutes / 1L).toInt()

}

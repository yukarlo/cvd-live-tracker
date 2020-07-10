package com.yukarlo.coronow.stack.local.repository

import com.squareup.sqldelight.db.SqlDriver
import com.yukarlo.core.domain.model.CasesContinentsModel
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.coronow.stack.database.Database
import javax.inject.Inject
import kotlin.time.minutes

@OptIn(kotlin.time.ExperimentalTime::class)
class CvdCasesLocalRepository @Inject constructor(
    sqlDriver: SqlDriver
) : ICvdCasesLocalRepository {

    private val database: Database = Database(driver = sqlDriver)

    override suspend fun addCountries(countries: List<CasesCountriesModel>) {
        val countriesQueries = database.cvdCountriesCasesQueries
        countriesQueries.deleteAllCountries()
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
                total_recovered = it.totalRecoveredCount
            )
        }
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

    override fun getCountries(): List<CasesCountriesModel> {
        val countriesQueries = database.cvdCountriesCasesQueries
        val countries = countriesQueries.selectAllCountries().executeAsList()

        return countries.map {
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
                totalActiveCount = it.total_active
            )
        }

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
            if (updated.minutes < 10.minutes) {
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
}

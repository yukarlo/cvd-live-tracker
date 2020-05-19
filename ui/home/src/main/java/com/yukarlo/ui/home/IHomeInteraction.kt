package com.yukarlo.ui.home

interface IHomeInteraction {
    fun navigateToCountries(continentName: String = "")
    fun navigateToSymptoms()
    fun navigateToPreventiveMeasures()
}

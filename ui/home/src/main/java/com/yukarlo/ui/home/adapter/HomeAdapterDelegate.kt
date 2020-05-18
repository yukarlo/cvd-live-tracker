package com.yukarlo.ui.home.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yukarlo.common.android.databinding.AffectedRowViewBinding
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.ui.home.IHomeInteraction
import com.yukarlo.ui.home.R
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.*
import com.yukarlo.ui.home.databinding.HomeAlternativeSummaryBinding
import com.yukarlo.ui.home.databinding.HomeContinentHeaderBinding
import com.yukarlo.ui.home.databinding.HomeHeaderBinding
import com.yukarlo.ui.home.databinding.HomeHealthTipsBinding

internal fun homeHeaderDelegate() =
    adapterDelegateViewBinding<Header, HomeBaseItem, HomeHeaderBinding>(
        { layoutInflater, root ->
            HomeHeaderBinding.inflate(
                layoutInflater,
                root,
                false
            )
        },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is Header && items[position] is Header
        }
    ) {}

internal fun homeSummaryDelegate(
    homeInteraction: IHomeInteraction,
    textProvider: TextProvider
) =
    adapterDelegateViewBinding<SummaryItem, HomeBaseItem, HomeAlternativeSummaryBinding>(
        { layoutInflater, root ->
            HomeAlternativeSummaryBinding.inflate(
                layoutInflater,
                root,
                false
            )
        },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is SummaryItem && items[position] is SummaryItem
        }
    ) {
        binding.homeSummaryLayout.setOnClickListener {
            homeInteraction.navigateToCountries()
        }
        bind {
            binding.homeConfirmedCount.text =
                textProvider.formatNumber(item.summary.totalCasesCount)
            binding.homeDeceasedCount.text =
                textProvider.formatNumber(item.summary.totalDeceasedCount)
            binding.homeRecoveredCount.text =
                textProvider.formatNumber(item.summary.totalRecoveredCount)
            binding.homeAffectedCountriesCount.text =
                textProvider.formatNumber(item.summary.affectedCountries)
            binding.homeUpdatedSince.text =
                String.format(getString(R.string.updated), item.summary.updatedSince)
        }
    }

internal fun homeHealthTipsDelegate(homeInteraction: IHomeInteraction) =
    adapterDelegateViewBinding<HealthTipsItem, HomeBaseItem, HomeHealthTipsBinding>(
        { layoutInflater, root ->
            HomeHealthTipsBinding.inflate(
                layoutInflater,
                root,
                false
            )
        },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is HealthTipsItem && items[position] is HealthTipsItem
        }
    ) {
        binding.homeHealthTipsPreventiveMeasureCardView.setOnClickListener {
            homeInteraction.navigateToPreventiveMeasures()
        }
        binding.homeHealthTipsSymptomsCardView.setOnClickListener {
            homeInteraction.navigateToSymptoms()
        }
    }

internal fun homeContinentHeader() =
    adapterDelegateViewBinding<ContinentsHeader, HomeBaseItem, HomeContinentHeaderBinding>(
        { layoutInflater, root ->
            HomeContinentHeaderBinding.inflate(
                layoutInflater,
                root,
                false
            )
        },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is ContinentsHeader && items[position] is ContinentsHeader
        }
    ) {

    }

internal fun homeContinentsDelegate(homeInteraction: IHomeInteraction, textProvider: TextProvider) =
    adapterDelegateViewBinding<ContinentsItem, HomeBaseItem, AffectedRowViewBinding>(
        { layoutInflater, root ->
            AffectedRowViewBinding.inflate(
                layoutInflater,
                root,
                false
            )
        },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is ContinentsItem && items[position] is ContinentsItem
        }
    ) {
        binding.affectedConstraintLayout.setOnClickListener {
            homeInteraction.navigateToContinents(continentName = item.continents.continentName)
        }
        bind {
            binding.affectedTotalCasesCount.text =
                textProvider.formatNumber(item.continents.totalCasesCount)
            binding.affectedTotalRecoveredCount.text =
                textProvider.formatNumber(item.continents.totalRecoveredCount)
            binding.affectedTotalDeceasedCount.text =
                textProvider.formatNumber(item.continents.totalDeceasedCount)
            binding.affectedTotalActiveCount.text =
                textProvider.formatNumber(item.continents.totalActiveCount)
            binding.affectedRegionName.text = item.continents.continentName
            binding.affectedChevron.isVisible = false
        }
    }

package com.yukarlo.ui.home.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yukarlo.common.android.databinding.AffectedRowViewBinding
import com.yukarlo.ui.home.IHomeInteraction
import com.yukarlo.ui.home.R
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.ContinentsHeader
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.ContinentsItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.Header
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.HealthTipsItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.SummaryItem
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

internal fun homeSummaryDelegate(homeInteraction: IHomeInteraction) =
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
            binding.homeConfirmedCount.text = item.summary.totalCasesCount
            binding.homeDeceasedCount.text = item.summary.totalDeceasedCount
            binding.homeRecoveredCount.text = item.summary.totalRecoveredCount
            binding.homeAffectedCountriesCount.text = item.summary.affectedCountries
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

internal fun homeContinentsDelegate(homeInteraction: IHomeInteraction) =
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
            binding.affectedTotalCasesCount.text = item.continents.totalCasesCount
            binding.affectedTotalRecoveredCount.text = item.continents.totalRecoveredCount
            binding.affectedTotalDeceasedCount.text = item.continents.totalDeceasedCount
            binding.affectedTotalActiveCount.text = item.continents.totalActiveCount
            binding.affectedRegionName.text = item.continents.continentName
            binding.affectedChevron.isVisible = false
        }
    }

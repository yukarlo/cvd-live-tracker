package com.yukarlo.ui.home.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yukarlo.common.android.databinding.AffectedAlternativeRowViewBinding
import com.yukarlo.common.android.databinding.AffectedRowViewBinding
import com.yukarlo.common.android.databinding.AffectedTitleRowViewBinding
import com.yukarlo.ui.home.R
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.*
import com.yukarlo.ui.home.databinding.HomeAlternativeSummaryBinding
import com.yukarlo.ui.home.databinding.HomeContinentHeaderBinding
import com.yukarlo.ui.home.databinding.HomeSummaryViewBinding

internal fun homeSummaryDelegate(itemClickedListener: () -> Unit) =
    adapterDelegateViewBinding<SummaryItem, HomeBaseItem, HomeAlternativeSummaryBinding>(
        { layoutInflater, root -> HomeAlternativeSummaryBinding.inflate(layoutInflater, root, false) },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is SummaryItem && items[position] is SummaryItem
        }
    ) {
        binding.homeSummaryLayout.setOnClickListener {
            itemClickedListener()
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

internal fun homeContinentsTitleDelegate() =
    adapterDelegateViewBinding<ContinentsTitle, HomeBaseItem, AffectedTitleRowViewBinding>(
        { layoutInflater, root ->
            AffectedTitleRowViewBinding.inflate(
                layoutInflater,
                root,
                false
            )
        },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is ContinentsTitle && items[position] is ContinentsTitle
        }
    ) {

    }

internal fun homeContinentsDelegate(itemClickedListener: (String) -> Unit) =
    adapterDelegateViewBinding<ContinentsItem, HomeBaseItem, AffectedAlternativeRowViewBinding>(
        { layoutInflater, root -> AffectedAlternativeRowViewBinding.inflate(layoutInflater, root, false) },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is ContinentsItem && items[position] is ContinentsItem
        }
    ) {
        binding.homeContinentsLayout.setOnClickListener {
            itemClickedListener(item.continents.continentName)
        }
        bind {
            binding.affectedTotalCasesCount.text = item.continents.totalCasesCount
            binding.affectedTotalRecoveredCount.text = item.continents.totalRecoveredCount
            binding.affectedTotalDeceasedCount.text = item.continents.totalDeceasedCount
            binding.affectedRegionName.text = item.continents.continentName
        }
    }

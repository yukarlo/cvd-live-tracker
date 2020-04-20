package com.yukarlo.ui.home.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yukarlo.ui.home.R
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.adapter.model.HomeBaseItem.*
import com.yukarlo.ui.home.databinding.HomeContinentHeaderBinding
import com.yukarlo.ui.home.databinding.HomeContinentsTitleViewBinding
import com.yukarlo.ui.home.databinding.HomeContinentsViewBinding
import com.yukarlo.ui.home.databinding.HomeSummaryViewBinding

internal fun homeSummaryDelegate(itemClickedListener: () -> Unit) =
    adapterDelegateViewBinding<SummaryItem, HomeBaseItem, HomeSummaryViewBinding>(
        { layoutInflater, root -> HomeSummaryViewBinding.inflate(layoutInflater, root, false) },
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
    adapterDelegateViewBinding<ContinentsTitle, HomeBaseItem, HomeContinentsTitleViewBinding>(
        { layoutInflater, root ->
            HomeContinentsTitleViewBinding.inflate(
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
    adapterDelegateViewBinding<ContinentsItem, HomeBaseItem, HomeContinentsViewBinding>(
        { layoutInflater, root -> HomeContinentsViewBinding.inflate(layoutInflater, root, false) },
        on = { item: HomeBaseItem, items: List<HomeBaseItem>, position: Int ->
            item is ContinentsItem && items[position] is ContinentsItem
        }
    ) {
        binding.homeContinentsLayout.setOnClickListener {
            itemClickedListener(item.continents.continentName)
        }
        bind {
            binding.homeContinentTotalCases.text = item.continents.totalCasesCount
            binding.homeContinentTotalRecovered.text = item.continents.totalRecoveredCount
            binding.homeContinentTotalDeceased.text = item.continents.totalDeceasedCount
            binding.homeContinentsName.text = item.continents.continentName
        }
    }

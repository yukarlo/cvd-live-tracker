package com.yukarlo.ui.home.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yukarlo.ui.home.R
import com.yukarlo.ui.home.adapter.HomeItem.SummaryItem
import com.yukarlo.ui.home.databinding.HomeSummaryViewBinding

internal fun homeSummaryDelegate() =
    adapterDelegateViewBinding<SummaryItem, HomeItem, HomeSummaryViewBinding>(
        { layoutInflater, root -> HomeSummaryViewBinding.inflate(layoutInflater, root, false) },
        on = { item: HomeItem, items: List<HomeItem>, position: Int ->
            item is SummaryItem && items[position] is SummaryItem
        }
    ) {
        bind {
            binding.homeConfirmedCount.text = item.summary.totalCasesCount
            binding.homeDeceasedCount.text = item.summary.totalDeceasedCount
            binding.homeRecoveredCount.text = item.summary.totalRecoveredCount
            binding.homeAffectedCountriesCount.text = item.summary.affectedCountries
            binding.homeUpdatedSince.text =
                String.format(getString(R.string.updated), item.summary.updatedSince)
        }
    }
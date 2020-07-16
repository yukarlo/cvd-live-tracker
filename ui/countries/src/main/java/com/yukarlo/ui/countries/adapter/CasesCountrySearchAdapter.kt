package com.yukarlo.ui.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yukarlo.ui.countries.ICountrySearchInteraction
import com.yukarlo.ui.countries.R
import com.yukarlo.ui.countries.SortBy
import com.yukarlo.ui.countries.adapter.CasesCountrySearchAdapter.CasesCountrySearchViewHolder
import com.yukarlo.ui.countries.databinding.SearchRowBinding

class CasesCountrySearchAdapter(val countrySearchInteraction: ICountrySearchInteraction) :
    RecyclerView.Adapter<CasesCountrySearchViewHolder>() {

    private var data: String = ""
    private lateinit var searchRowBinding: SearchRowBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CasesCountrySearchViewHolder {
        searchRowBinding =
            SearchRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasesCountrySearchViewHolder(searchRowBinding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: CasesCountrySearchViewHolder, position: Int) {
        holder.bind(title = data)
    }

    fun updateSortTitle(sortBy: SortBy) {
        data = searchRowBinding.root.resources.getString(
            when (sortBy) {
                SortBy.Active -> R.string.active
                SortBy.Confirmed -> R.string.confirmed
                SortBy.Deceased -> R.string.deceased
                SortBy.Recovered -> R.string.recovered
                SortBy.Country -> R.string.countries
            }
        )
    }

    inner class CasesCountrySearchViewHolder(private val itemBinding: SearchRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(title: String) {
            itemBinding.affectedPlaceSortTextView.apply {
                text = title
                setOnClickListener {
                    countrySearchInteraction.showSortCountryBottomSheet()
                }
            }
        }
    }
}

package com.yukarlo.ui.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yukarlo.ui.countries.ICountrySortInteraction
import com.yukarlo.ui.countries.R
import com.yukarlo.core.domain.model.SortBy
import com.yukarlo.ui.countries.adapter.CasesCountryOptionsAdapter.CasesCountrySearchViewHolder
import com.yukarlo.ui.countries.databinding.SearchRowBinding

class CasesCountryOptionsAdapter(val countrySortInteraction: ICountrySortInteraction) :
    RecyclerView.Adapter<CasesCountrySearchViewHolder>() {

    private lateinit var data: SortBy

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CasesCountrySearchViewHolder {
        val searchRowBinding =
            SearchRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasesCountrySearchViewHolder(searchRowBinding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: CasesCountrySearchViewHolder, position: Int) {
        holder.bind(data = data)
    }

    fun updateSortTitle(sortBy: SortBy) {
        data = sortBy
    }

    inner class CasesCountrySearchViewHolder(private val itemBinding: SearchRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: SortBy) {
            val title = itemBinding.root.resources.getString(
                when (data) {
                    SortBy.Active -> R.string.active
                    SortBy.Confirmed -> R.string.confirmed
                    SortBy.Deceased -> R.string.deceased
                    SortBy.Recovered -> R.string.recovered
                    SortBy.Country -> R.string.sort_default
                }
            )

            itemBinding.affectedPlaceSortTextView.apply {
                text = title
                setOnClickListener {
                    countrySortInteraction.showSortCountryBottomSheet()
                }
            }
        }
    }
}

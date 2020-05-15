package com.yukarlo.ui.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.yukarlo.ui.countries.ICountrySearchInteraction
import com.yukarlo.ui.countries.adapter.CasesCountrySearchAdapter.CasesCountrySearchViewHolder
import com.yukarlo.ui.countries.databinding.SearchRowBinding

class CasesCountrySearchAdapter(val countrySearchInteraction: ICountrySearchInteraction) :
    RecyclerView.Adapter<CasesCountrySearchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CasesCountrySearchViewHolder {
        val itemBinding =
            SearchRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasesCountrySearchViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: CasesCountrySearchViewHolder, position: Int) {
        holder.bind()
    }

    inner class CasesCountrySearchViewHolder(private val itemBinding: SearchRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {
            itemBinding.affectedPlaceSearchTextInput.doAfterTextChanged {
                countrySearchInteraction.filterCountry(it.toString())
            }
        }
    }
}

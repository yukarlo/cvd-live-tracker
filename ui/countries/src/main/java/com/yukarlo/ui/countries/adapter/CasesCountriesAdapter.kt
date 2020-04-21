package com.yukarlo.ui.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.yukarlo.common.android.databinding.AffectedRowViewBinding
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter.CasesCountriesViewHolder

class CasesCountriesAdapter() : RecyclerView.Adapter<CasesCountriesViewHolder>() {

    private val data = arrayListOf<CasesCountriesModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CasesCountriesViewHolder {
        val itemBinding =
            AffectedRowViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasesCountriesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: CasesCountriesViewHolder, position: Int) {
        val casesCountriesData: CasesCountriesModel = data[position]
        holder.bind(data = casesCountriesData)
    }

    fun updateData(items: List<CasesCountriesModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class CasesCountriesViewHolder(private val itemBinding: AffectedRowViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: CasesCountriesModel) {
            itemBinding.affectedRegionName.text = data.countryName
            itemBinding.affectedTotalCasesCount.text = data.totalCasesCount
            itemBinding.affectedTotalRecoveredCount.text = data.totalRecoveredCount
            itemBinding.affectedTotalDeceasedCount.text = data.totalDeceasedCount
            itemBinding.affectedRegionFlag.apply {
                load(data.countryFlag)
                visibility = View.VISIBLE
            }
        }
    }
}
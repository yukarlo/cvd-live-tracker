package com.yukarlo.ui.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.yukarlo.common.android.databinding.AffectedRowViewBinding
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.common.android.view.collapse
import com.yukarlo.common.android.view.expand
import com.yukarlo.common.android.view.rotate
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter.CasesCountriesViewHolder
import javax.inject.Inject

class CasesCountriesAdapter @Inject constructor(
    val textProvider: TextProvider
) : RecyclerView.Adapter<CasesCountriesViewHolder>() {

    private val data = arrayListOf<CasesCountriesModel>()
    private var mExpandedPosition = -1
    private var expandedHeight = -1

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
            itemBinding.affectedTotalCasesCount.text =
                textProvider.formatNumber(data.totalCasesCount)
            itemBinding.affectedTotalRecoveredCount.text =
                textProvider.formatNumber(data.totalRecoveredCount)
            itemBinding.affectedTotalDeceasedCount.text =
                textProvider.formatNumber(data.totalDeceasedCount)
            itemBinding.affectedTotalActiveCount.text =
                textProvider.formatNumber(data.totalActiveCount)
            itemBinding.affectedTotalTodayCasesCount.text =
                textProvider.provideCasesTodayString(data.totalTodayCases)
            itemBinding.affectedTotalTodayDeceasedCount.text =
                textProvider.provideCasesTodayString(data.totalTodayDeceased)
            itemBinding.affectedRegionFlag.apply {
                load(data.countryFlag)
                visibility = View.VISIBLE
            }

            getHiddenViewHeight()

            val isExpanded = layoutPosition == mExpandedPosition

            if (isExpanded) {
                itemBinding.affectedExpand.expand(expandedHeight = expandedHeight)
            } else {
                itemBinding.affectedExpand.collapse(expandedHeight = expandedHeight)
            }

            itemBinding.affectedConstraintLayout.setOnClickListener {
                mExpandedPosition = if (isExpanded) {
                    itemBinding.affectedChevron.rotate(from = 180f, to = 0f)
                    -1
                } else {
                    itemBinding.affectedChevron.rotate(from = 0f, to = 180f)
                    layoutPosition
                }
                notifyDataSetChanged()
            }
        }

        private fun getHiddenViewHeight() {
            if (expandedHeight < 0) {
                expandedHeight = 0

                itemBinding.affectedConstraintLayout.doOnLayout { view ->

                    val expandedView = itemBinding.affectedExpand
                    expandedView.isVisible = true
                    view.doOnPreDraw {
                        expandedHeight = expandedView.height
                        expandedView.isVisible = false
                    }
                }
            }
        }
    }
}
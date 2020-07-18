package com.yukarlo.ui.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.yukarlo.common.android.databinding.AffectedRowViewBinding
import com.yukarlo.common.android.extension.getDrawableCompat
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.core.domain.model.CasesCountriesModel
import com.yukarlo.core.domain.model.FavoriteCountry
import com.yukarlo.ui.countries.ICountryFavoriteInteraction
import com.yukarlo.ui.countries.R
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter.CasesCountriesViewHolder
import javax.inject.Inject

class CasesCountriesAdapter @Inject constructor(
    val countryFavoriteInteraction: ICountryFavoriteInteraction,
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
            itemBinding.affectedFavorite.setImageDrawable(
                if (data.isFavorite) {
                    itemBinding.root.resources.getDrawableCompat(R.drawable.ic_star_filled)
                } else {
                    itemBinding.root.resources.getDrawableCompat(R.drawable.ic_star_unfilled)
                }
            )

            itemBinding.affectedFavorite.setOnClickListener {
                countryFavoriteInteraction.addToFavorites(
                    country = FavoriteCountry(
                        countryIso = data.countryIso,
                        addToFavorite = !data.isFavorite
                    )
                )
                itemBinding.affectedFavorite.setImageDrawable(
                    if (!data.isFavorite) {
                        itemBinding.root.resources.getDrawableCompat(R.drawable.ic_star_filled)
                    } else {
                        itemBinding.root.resources.getDrawableCompat(R.drawable.ic_star_unfilled)
                    }
                )
            }
        }
    }
}
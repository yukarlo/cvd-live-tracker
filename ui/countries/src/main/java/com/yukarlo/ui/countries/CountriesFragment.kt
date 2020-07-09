package com.yukarlo.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter
import com.yukarlo.ui.countries.adapter.CasesCountrySearchAdapter
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_sorting.*
import javax.inject.Inject

@AndroidEntryPoint
class CountriesFragment : Fragment(), ICountrySearchInteraction {

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: CountriesViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentBinding: CountriesFragmentBinding
    private lateinit var casesCountriesAdapter: CasesCountriesAdapter
    private lateinit var casesSearchCountryAdapter: CasesCountrySearchAdapter
    private lateinit var sheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = CountriesFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViews()
        setupObservers()
    }

    private fun setUpViews() {
        casesCountriesAdapter = CasesCountriesAdapter(textProvider = mTextProvider)
        casesSearchCountryAdapter = CasesCountrySearchAdapter(countrySearchInteraction = this)

        val concatAdapter = ConcatAdapter(casesSearchCountryAdapter, casesCountriesAdapter)

        recyclerView = fragmentBinding.countriesRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = concatAdapter
        }
    }

    private fun setupObservers() {
        mViewModel.stateLiveData.observe(viewLifecycleOwner, {
            casesCountriesAdapter.updateData(items = it.countries)
        })

        mViewModel.onContinentNameUpdated.observe(viewLifecycleOwner, { continentName ->
            (activity as AppCompatActivity).supportActionBar?.title = continentName
        })
    }

    override fun filterCountry(query: String) {
        mViewModel.filterCountry(filter = query)
    }

    override fun showSortCountryBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_sorting, null)
        BottomSheetDialog(requireContext()).run {
            setContentView(view)

            sortGroup.setOnCheckedChangeListener { _: RadioGroup, checkedId: Int ->
                when (checkedId) {
                    R.id.sortByCountry -> {
                        mViewModel.sortCountry(sortBy = SortBy.Country)
                        dismissWithAnimation
                    }
                    R.id.sortByConfirmed -> {
                        mViewModel.sortCountry(sortBy = SortBy.Confirmed)
                        dismissWithAnimation
                    }
                    R.id.sortByDeceased -> {
                        mViewModel.sortCountry(sortBy = SortBy.Deceased)
                        dismissWithAnimation
                    }
                    R.id.sortByRecovered -> {
                        mViewModel.sortCountry(sortBy = SortBy.Recovered)
                        dismissWithAnimation
                    }
                }
            }

            show()
        }
    }
}
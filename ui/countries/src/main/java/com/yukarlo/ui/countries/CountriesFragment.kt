package com.yukarlo.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yukarlo.lib.cases.di.DaggerLibCvdCasesComponent
import com.yukarlo.main.di.CoreComponentFactory
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter
import com.yukarlo.ui.countries.adapter.CasesCountrySearchAdapter
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding
import com.yukarlo.ui.countries.di.DaggerUiCountriesComponent
import javax.inject.Inject


class CountriesFragment : Fragment(), ICountrySearchInteraction {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mViewModel: CountriesViewModel by viewModels { mViewModelFactory }

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentBinding: CountriesFragmentBinding
    private lateinit var casesCountriesAdapter: CasesCountriesAdapter
    private lateinit var casesSearchCountryAdapter: CasesCountrySearchAdapter
    private lateinit var sheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val coreComponent = CoreComponentFactory.coreComponent(context = requireContext())
        DaggerUiCountriesComponent.factory()
            .create(
                countriesFragment = this,
                coreComponent = coreComponent,
                libCvdCasesComponent = DaggerLibCvdCasesComponent.factory().create(coreComponent)
            )
            .inject(fragment = this)

        fragmentBinding = CountriesFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViews()
        setupObservers()
    }

    private fun setUpViews() {
        casesCountriesAdapter = CasesCountriesAdapter()
        casesSearchCountryAdapter = CasesCountrySearchAdapter(countrySearchInteraction = this)

        val mergeAdapter = MergeAdapter(casesSearchCountryAdapter, casesCountriesAdapter)

        recyclerView = fragmentBinding.countriesRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = mergeAdapter
        }
    }

    private fun setupObservers() {
        mViewModel.onCountryUpdated.observe(viewLifecycleOwner, Observer { countries ->
            casesCountriesAdapter.updateData(items = countries)
        })
    }

    override fun filterCountry(query: String) {
        mViewModel.filterCountry(filter = query)
    }

    override fun showSortCountryBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_sorting, null)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(view)
        dialog.show()
    }
}
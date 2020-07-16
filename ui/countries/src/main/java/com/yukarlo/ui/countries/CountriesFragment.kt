package com.yukarlo.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter
import com.yukarlo.ui.countries.adapter.CasesCountrySearchAdapter
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_sorting.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        fragmentBinding = CountriesFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViews()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        with(menu) {
            clear()
            inflater.inflate(R.menu.menu_main, this)
            findItem(R.id.action_information).isVisible = false

            val searchView = menu.findItem(R.id.action_search).run {
                isVisible = true
                actionView as SearchView
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    mViewModel.filterCountry(filter = newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    mViewModel.filterCountry(filter = query)
                    return true
                }
            })

            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    private fun setUpViews() {
        casesCountriesAdapter = CasesCountriesAdapter(textProvider = mTextProvider)
        casesSearchCountryAdapter = CasesCountrySearchAdapter(countrySearchInteraction = this)

        val concatAdapter = ConcatAdapter(casesSearchCountryAdapter, casesCountriesAdapter)

        recyclerView = fragmentBinding.countriesRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = concatAdapter
        }

        val view = layoutInflater.inflate(R.layout.bottom_sheet_sorting, null)
        bottomSheetDialog = BottomSheetDialog(requireContext()).apply {
            setContentView(view)

            sortGroup.setOnCheckedChangeListener { _: RadioGroup, checkedId: Int ->
                when (checkedId) {
                    R.id.sortByCountry -> {
                        mViewModel.intentChannel.offer(CountriesViewEvent.SortedBy(sortBy = SortBy.Country))
                    }
                    R.id.sortByConfirmed -> {
                        mViewModel.intentChannel.offer(CountriesViewEvent.SortedBy(sortBy = SortBy.Confirmed))
                    }
                    R.id.sortByDeceased -> {
                        mViewModel.intentChannel.offer(CountriesViewEvent.SortedBy(sortBy = SortBy.Deceased))
                    }
                    R.id.sortByRecovered -> {
                        mViewModel.intentChannel.offer(CountriesViewEvent.SortedBy(sortBy = SortBy.Recovered))
                    }
                    R.id.sortByActive -> {
                        mViewModel.intentChannel.offer(CountriesViewEvent.SortedBy(sortBy = SortBy.Active))
                    }
                }
                dismiss()
            }
        }
    }

    private fun setupObservers() {
        mViewModel.onUiStateUpdated
            .onEach { state ->  renderUiState(countriesViewState = state) }
            .launchIn(lifecycleScope)

        mViewModel.onUiEventUpdated.observe(viewLifecycleOwner, {
            renderUiEvent(countriesViewEvent = it)
        })
    }

    private fun renderUiState(countriesViewState: CountriesViewState) {
        with(countriesViewState) {
            casesCountriesAdapter.updateData(items = countriesViewState.countries)
        }
    }

    private fun renderUiEvent(countriesViewEvent: CountriesViewEvent) {
        when (countriesViewEvent) {
            is CountriesViewEvent.SortedBy -> {
                casesSearchCountryAdapter.updateSortTitle(sortBy = countriesViewEvent.sortBy)
            }
            is CountriesViewEvent.ContinentName -> (activity as AppCompatActivity).supportActionBar?.title =
                countriesViewEvent.continentName
        }
    }

    override fun filterCountry(query: String) {
        mViewModel.filterCountry(filter = query)
    }

    override fun showSortCountryBottomSheet() {
        bottomSheetDialog.show()
    }
}
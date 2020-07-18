package com.yukarlo.ui.countries

import android.view.Menu
import android.view.MenuInflater
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yukarlo.base.BaseFragment
import com.yukarlo.base.viewBinding
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.core.domain.model.FavoriteCountry
import com.yukarlo.core.domain.model.SortBy
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter
import com.yukarlo.ui.countries.adapter.CasesCountryOptionsAdapter
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_sorting.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
internal class CountriesFragment
    : BaseFragment<CountriesViewState>(contentLayoutId = R.layout.countries_fragment),
    ICountrySortInteraction, ICountryFavoriteInteraction {

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: CountriesViewModel by viewModels()
    private val fragmentBinding: CountriesFragmentBinding by viewBinding(CountriesFragmentBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var casesCountriesAdapter: CasesCountriesAdapter
    private lateinit var casesSearchCountryAdapter: CasesCountryOptionsAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog

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
                    filterCountry(query = newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    filterCountry(query = query)
                    return true
                }
            })

            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun setUpViews() {
        casesCountriesAdapter =
            CasesCountriesAdapter(textProvider = mTextProvider, countryFavoriteInteraction = this)
        casesSearchCountryAdapter = CasesCountryOptionsAdapter(countrySortInteraction = this)

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
                    R.id.sortByCountry -> sortCountriesBy(sortBy = SortBy.Country)
                    R.id.sortByConfirmed -> sortCountriesBy(sortBy = SortBy.Confirmed)
                    R.id.sortByDeceased -> sortCountriesBy(sortBy = SortBy.Deceased)
                    R.id.sortByRecovered -> sortCountriesBy(sortBy = SortBy.Recovered)
                    R.id.sortByActive -> sortCountriesBy(sortBy = SortBy.Active)
                }
                dismiss()
            }
        }
    }

    override fun setUpObservers() {
        mViewModel.onUiStateUpdated
            .onEach { state -> render(state = state) }
            .launchIn(lifecycleScope)

        mViewModel.onContinentNameUpdated.observe(viewLifecycleOwner, {
            (activity as AppCompatActivity).supportActionBar?.title = it
        })
    }

    override fun render(state: CountriesViewState) {
        with(state) {
            casesCountriesAdapter.updateData(items = state.countries)
            casesSearchCountryAdapter.updateSortTitle(sortBy = state.sortBy)
        }
    }

    private fun filterCountry(query: String) {
        mViewModel.sendAction(CountriesViewAction.FilterCountries(query = query))
    }

    private fun sortCountriesBy(sortBy: SortBy) {
        mViewModel.sendAction(CountriesViewAction.SortCountriesBy(sortBy = sortBy))
    }

    override fun showSortCountryBottomSheet() {
        bottomSheetDialog.show()
    }

    override fun addToFavorites(country: FavoriteCountry) {
        mViewModel.sendAction(CountriesViewAction.AddToFavorite(country = country))
    }
}
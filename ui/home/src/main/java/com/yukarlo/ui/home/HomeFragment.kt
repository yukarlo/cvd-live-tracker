package com.yukarlo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.yukarlo.common.android.CountriesInputModel
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.ui.home.adapter.*
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), IHomeInteraction {

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: HomeViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentBinding: HomeFragmentBinding
    private lateinit var homeAdapter: ListDelegationAdapter<List<HomeBaseItem>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setupObservers()
    }

    private fun setUpViews() {
        recyclerView = fragmentBinding.homeRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
        }

        homeAdapter = ListDelegationAdapter(
            homeHeaderDelegate(),
            homeSummaryDelegate(homeInteraction = this, textProvider = mTextProvider),
            homeHealthTipsDelegate(homeInteraction = this),
            homeContinentHeader(),
            homeContinentsDelegate(homeInteraction = this, textProvider = mTextProvider)
        )

        fragmentBinding.swipeHomeLayout.setOnRefreshListener {
            refreshData()
        }

        fragmentBinding.homeRetry.setOnClickListener {
            refreshData()
        }
    }

    private fun setupObservers() {
        mViewModel.onUiStateUpdated.observe(viewLifecycleOwner, {
            renderUiState(it)
        })
    }

    private fun renderUiState(it: HomeViewState) {
        fragmentBinding.swipeHomeLayout.isRefreshing = it.isLoading
        fragmentBinding.homeRecyclerView.isVisible = it.homeItems.isNotEmpty()
        fragmentBinding.homeError.isVisible = it.isError
        fragmentBinding.homeRetry.isVisible = it.isError

        homeAdapter.items = it.homeItems
        recyclerView.adapter = homeAdapter
    }

    private fun refreshData() {
        mViewModel.refreshData()
    }

    override fun navigateToCountries(continentName: String) {
        val bundle = bundleOf(
            "continent" to CountriesInputModel(
                mContinentName = continentName
            )
        )
        findNavController().navigate(R.id.action_Summary_to_CountriesFragment, bundle)
    }

    override fun navigateToSymptoms() {
        findNavController().navigate(R.id.action_Symptoms_to_SymptomsFragment)
    }

    override fun navigateToPreventiveMeasures() {
        findNavController().navigate(R.id.action_Preventive_Measures_to_PreventiveMeasuresFragment)
    }
}

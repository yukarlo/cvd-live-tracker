package com.yukarlo.ui.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.yukarlo.base.BaseFragment
import com.yukarlo.base.viewBinding
import com.yukarlo.common.android.CountriesInputModel
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.ui.home.adapter.homeContinentHeader
import com.yukarlo.ui.home.adapter.homeContinentsDelegate
import com.yukarlo.ui.home.adapter.homeHeaderDelegate
import com.yukarlo.ui.home.adapter.homeHealthTipsDelegate
import com.yukarlo.ui.home.adapter.homeSummaryDelegate
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeFragment
    : BaseFragment<HomeViewState>(contentLayoutId = R.layout.home_fragment), IHomeInteraction {

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: HomeViewModel by viewModels()
    private val fragmentBinding: HomeFragmentBinding by viewBinding(HomeFragmentBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: ListDelegationAdapter<List<HomeBaseItem>>

    override fun setUpViews() {
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
            mViewModel.sendAction(HomeViewAction.Refresh)
        }

        fragmentBinding.homeRetry.setOnClickListener {
            mViewModel.sendAction(HomeViewAction.Retry)
        }
    }

    override fun setUpObservers() {
        mViewModel.onUiStateUpdated
            .onEach { state -> render(state = state) }
            .launchIn(lifecycleScope)

        mViewModel.onNavigate.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navDirections ->
                findNavController().navigate(navDirections)
            }
        }
    }

    override fun render(state: HomeViewState) {
        fragmentBinding.swipeHomeLayout.isRefreshing = state.isLoading
        fragmentBinding.homeRecyclerView.isVisible = state.homeItems.isNotEmpty()
        fragmentBinding.homeError.isVisible = state.isError
        fragmentBinding.homeRetry.isVisible = state.isError

        homeAdapter.items = state.homeItems
        recyclerView.adapter = homeAdapter
    }

    override fun navigateToCountries(continentName: String) {
        mViewModel.sendAction(
            viewAction = HomeViewAction.Navigate(
                to = HomeFragmentDirections.actionSummaryToCountriesFragment(
                    CountriesInputModel(
                        mContinentName = continentName
                    )
                )
            )
        )
    }

    override fun navigateToSymptoms() {
        mViewModel.sendAction(
            viewAction = HomeViewAction.Navigate(
                to = HomeFragmentDirections.actionSymptomsToSymptomsFragment()
            )
        )
    }

    override fun navigateToPreventiveMeasures() {
        mViewModel.sendAction(
            viewAction = HomeViewAction.Navigate(
                to = HomeFragmentDirections.actionPreventiveMeasuresToPreventiveMeasuresFragment()
            )
        )
    }
}

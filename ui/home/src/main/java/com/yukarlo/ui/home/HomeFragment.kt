package com.yukarlo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.yukarlo.lib.cases.di.DaggerLibCvdCasesComponent
import com.yukarlo.main.di.CoreComponentFactory
import com.yukarlo.ui.home.adapter.homeContinentHeader
import com.yukarlo.ui.home.adapter.homeContinentsDelegate
import com.yukarlo.ui.home.adapter.homeHeaderDelegate
import com.yukarlo.ui.home.adapter.homeHealthTipsDelegate
import com.yukarlo.ui.home.adapter.homeSummaryDelegate
import com.yukarlo.ui.home.adapter.model.HomeBaseItem
import com.yukarlo.ui.home.databinding.HomeFragmentBinding
import com.yukarlo.ui.home.di.DaggerUiHomeComponent
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mViewModel: HomeViewModel by viewModels { mViewModelFactory }

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentBinding: HomeFragmentBinding
    private lateinit var homeAdapter: ListDelegationAdapter<List<HomeBaseItem>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val coreComponent = CoreComponentFactory.coreComponent(context = requireContext())
        DaggerUiHomeComponent.factory()
            .create(
                homeFragment = this,
                coreComponent = coreComponent,
                libCvdCasesComponent = DaggerLibCvdCasesComponent.factory().create(coreComponent)
            )
            .inject(fragment = this)

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
            homeSummaryDelegate(navigateToCountries()),
            homeHealthTipsDelegate(),
            homeContinentHeader(),
            homeContinentsDelegate(navigateToContinents())
        )

        mViewModel.initView()
    }

    private fun setupObservers() {
        mViewModel.homeBla.observe(viewLifecycleOwner, Observer { homeItems ->
            homeAdapter.items = homeItems
            recyclerView.adapter = homeAdapter
        })
    }

    private fun navigateToCountries(): () -> Unit = {
        findNavController().navigate(R.id.action_Summary_to_CountriesFragment)
    }

    private fun navigateToContinents(): (String) -> Unit = {
        val bundle = bundleOf("continent" to it)
        findNavController().navigate(R.id.action_Continents_to_ContinentsFragment, bundle)
    }
}

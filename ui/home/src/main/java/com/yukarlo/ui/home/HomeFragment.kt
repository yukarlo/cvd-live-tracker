package com.yukarlo.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.yukarlo.core.di.CoreComponentFactory
import com.yukarlo.lib.cases.data.model.CasesSummaryResponseModel
import com.yukarlo.ui.home.databinding.HomeFragmentBinding
import com.yukarlo.ui.home.di.DaggerUiHomeComponent
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var fragmentBinding: HomeFragmentBinding
    private lateinit var mViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val coreComponent = CoreComponentFactory.coreComponent(context = requireContext())
        DaggerUiHomeComponent.factory()
            .create(
                homeFragment = this,
                coreComponent = coreComponent
            )
            .inject(fragment = this)

        fragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this, mViewModelFactory).get(HomeViewModel::class.java)

        mViewModel.getSummary().observe(viewLifecycleOwner, Observer {
            populateData(data = it)
        })
    }

    private fun populateData(data: CasesSummaryResponseModel) {
        with(fragmentBinding) {
            homeConfirmedCount.text = data.cases.toString()
            homeDeathCount.text = data.deaths.toString()
            homeRecoveredCount.text = data.recovered.toString()
        }
    }
}

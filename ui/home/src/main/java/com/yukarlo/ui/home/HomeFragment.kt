package com.yukarlo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yukarlo.core.di.CoreComponentFactory
import com.yukarlo.core.domain.model.CasesSummaryModel
import com.yukarlo.lib.cases.di.DaggerLibCvdCasesComponent
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
                coreComponent = coreComponent,
                libCvdCasesComponent = DaggerLibCvdCasesComponent.factory().create(coreComponent)
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

    private fun populateData(data: CasesSummaryModel) {
        with(fragmentBinding) {
            homeConfirmedCount.text = data.totalCasesCount
            homeDeceasedCount.text = data.totalDeceasedCount
            homeRecoveredCount.text = data.totalRecoveredCount
            homeAffectedCountriesCount.text = data.affectedCountries
            homeUpdatedSince.text = String.format(resources.getString(R.string.updated), data.updatedSince)
        }
    }
}

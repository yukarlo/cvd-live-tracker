package com.yukarlo.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.yukarlo.core.di.CoreComponentFactory
import com.yukarlo.lib.cases.di.DaggerLibCvdCasesComponent
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding
import com.yukarlo.ui.countries.di.DaggerUiCountriesComponent
import javax.inject.Inject

class CountriesFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var fragmentBinding: CountriesFragmentBinding
    private val mViewModel: CountriesViewModel by viewModels { mViewModelFactory }

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

        // TODO: Use the ViewModel
    }

}
package com.yukarlo.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yukarlo.main.di.CoreComponentFactory
import com.yukarlo.lib.cases.di.DaggerLibCvdCasesComponent
import com.yukarlo.ui.countries.adapter.CasesCountriesAdapter
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding
import com.yukarlo.ui.countries.di.DaggerUiCountriesComponent
import javax.inject.Inject

class CountriesFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mViewModel: CountriesViewModel by viewModels { mViewModelFactory }

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentBinding: CountriesFragmentBinding
    private lateinit var casesCountriesAdapter: CasesCountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val coreComponent = com.yukarlo.main.di.CoreComponentFactory.coreComponent(context = requireContext())
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

        casesCountriesAdapter = CasesCountriesAdapter()
        recyclerView = fragmentBinding.countriesRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = casesCountriesAdapter
        }

        mViewModel.getAll().observe(viewLifecycleOwner, Observer { countries ->
            casesCountriesAdapter.updateData(items = countries)
        })
    }

}
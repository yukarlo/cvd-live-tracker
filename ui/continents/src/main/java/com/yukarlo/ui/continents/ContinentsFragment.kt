package com.yukarlo.ui.continents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.coronow.stack.cases.di.DaggerUseCaseComponent
import com.yukarlo.main.di.CoreComponentFactory
import com.yukarlo.ui.continents.adapter.CasesContinentAdapter
import com.yukarlo.ui.continents.databinding.ContinentsFragmentBinding
import com.yukarlo.ui.continents.di.DaggerUiContinentsComponent
import javax.inject.Inject

class ContinentsFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: ContinentsViewModel by viewModels { mViewModelFactory }

    private lateinit var fragmentBinding: ContinentsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var casesContinentAdapter: CasesContinentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val coreComponent = CoreComponentFactory.coreComponent(context = requireContext())
        DaggerUiContinentsComponent.factory()
            .create(
                continentsFragment = this,
                coreComponent = coreComponent,
                useCaseComponent = DaggerUseCaseComponent.factory()
                    .create(coreComponent = coreComponent)
            )
            .inject(fragment = this)

        fragmentBinding = ContinentsFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViews()
        setupObservers()
    }

    private fun setUpViews() {
        casesContinentAdapter =
            CasesContinentAdapter(textProvider = mTextProvider)

        recyclerView = fragmentBinding.countriesRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = casesContinentAdapter
        }
    }

    private fun setupObservers() {
        mViewModel.onCountryUpdated.observe(viewLifecycleOwner, Observer { countries ->
            casesContinentAdapter.updateData(items = countries)
        })

        mViewModel.onContinentNameUpdated.observe(viewLifecycleOwner, Observer { continentName ->
            (activity as AppCompatActivity).supportActionBar?.title = continentName
        })
    }
}

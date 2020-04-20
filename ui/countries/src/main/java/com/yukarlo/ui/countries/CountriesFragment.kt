package com.yukarlo.ui.countries

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yukarlo.ui.countries.databinding.CountriesFragmentBinding

class CountriesFragment : Fragment() {

    private lateinit var fragmentBinding: CountriesFragmentBinding
    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = CountriesFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
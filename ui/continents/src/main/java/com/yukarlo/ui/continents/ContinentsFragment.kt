package com.yukarlo.ui.continents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yukarlo.ui.continents.databinding.ContinentsFragmentBinding

class ContinentsFragment : Fragment() {

    private lateinit var fragmentBinding: ContinentsFragmentBinding
    private lateinit var viewModel: ContinentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = ContinentsFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ContinentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
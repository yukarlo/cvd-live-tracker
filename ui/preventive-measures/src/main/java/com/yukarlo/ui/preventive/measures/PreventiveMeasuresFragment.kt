package com.yukarlo.ui.preventive.measures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yukarlo.ui.preventive.measures.databinding.PreventiveMeasuresFragmentBinding

class PreventiveMeasuresFragment : Fragment() {

    private lateinit var fragmentBinding: PreventiveMeasuresFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = PreventiveMeasuresFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {

    }
}
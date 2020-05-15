package com.yukarlo.ui.symptoms

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yukarlo.ui.symptoms.databinding.SymptomsFragmentBinding

class SymptomsFragment : Fragment() {

    private lateinit var fragmentBinding: SymptomsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = SymptomsFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragmentBinding.symptomsMoreInformationTextView.movementMethod =
            LinkMovementMethod.getInstance()
    }
}

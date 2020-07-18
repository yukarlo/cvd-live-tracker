package com.yukarlo.ui.preventive.measures

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yukarlo.base.viewBinding
import com.yukarlo.ui.preventive.measures.databinding.PreventiveMeasuresFragmentBinding

class PreventiveMeasuresFragment : Fragment(R.layout.preventive_measures_fragment) {

    private val fragmentBinding: PreventiveMeasuresFragmentBinding by viewBinding(PreventiveMeasuresFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding.preventiveMeasuresMoreInformationTextView.movementMethod =
            LinkMovementMethod.getInstance()
    }
}

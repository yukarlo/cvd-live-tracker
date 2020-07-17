package com.yukarlo.ui.symptoms

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import com.yukarlo.base.viewBinding
import com.yukarlo.ui.symptoms.databinding.SymptomsFragmentBinding

class SymptomsFragment : Fragment(R.layout.symptoms_fragment) {

    private val fragmentBinding: SymptomsFragmentBinding by viewBinding(SymptomsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding.symptomsMoreInformationTextView.movementMethod =
            LinkMovementMethod.getInstance()
    }
}

package com.yukarlo.ui.country.details.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yukarlo.ui.country.details.CountryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalLayout::class)
@AndroidEntryPoint
internal class CountryDetailsComposeFragment : Fragment() {

    private lateinit var mComposeView: ComposeView

    private val mViewModel: CountryDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mComposeView = ComposeView(context = requireContext())
        return mComposeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        mViewModel.onUiStateUpdated
            .onEach { state ->
                mComposeView.setContent {
                    countryDetailsConstraintLayout(details = state.details)
                }
            }
            .launchIn(lifecycleScope)
    }
}

package com.yukarlo.ui.country.details

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.yukarlo.base.BaseFragment
import com.yukarlo.base.viewBinding
import com.yukarlo.common.android.text.TextProvider
import com.yukarlo.ui.country.details.databinding.CountryDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
internal class CountryDetailsFragment :
    BaseFragment<CountryDetailsViewState, Unit>(R.layout.country_details_fragment) {

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: CountryDetailsViewModel by viewModels()
    private val fragmentBinding: CountryDetailsFragmentBinding by viewBinding(
        CountryDetailsFragmentBinding::bind
    )

    override fun setUpViews() {
        fragmentBinding.CountryDetailsCountryImageView.isVisible = true
    }

    override fun setUpObservers() {
        mViewModel.onUiStateUpdated
            .onEach { state -> render(state = state) }
            .launchIn(lifecycleScope)
    }

    override fun render(state: CountryDetailsViewState) {
        when {
            state.fetchStatus != FetchStatus.Idle -> {
                with(state.details) {
                    fragmentBinding.CountryDetailsCountryHeader.text = countryName
                    fragmentBinding.CountryDetailsActiveCount.text =
                        mTextProvider.formatNumber(totalActiveCount)
                    fragmentBinding.CountryDetailsConfirmedCasesCount.text =
                        mTextProvider.formatNumber(totalCasesCount)
                    fragmentBinding.CountryDetailsDeceasedCount.text =
                        mTextProvider.formatNumber(totalDeceasedCount)
                    fragmentBinding.CountryDetailsRecoveredCount.text =
                        mTextProvider.formatNumber(totalRecoveredCount)
                    fragmentBinding.CountryDetailsCriticalCount.text =
                        mTextProvider.formatNumber(critical)
                    fragmentBinding.CountryDetailsTestsCount.text =
                        mTextProvider.formatNumber(tests)
                    fragmentBinding.CountryDetailsCountryImageView.load(countryFlag)
                }
            }
            else -> mViewModel.sendAction(CountryDetailsViewAction.LoadDetails)
        }
    }

    override fun renderSideEffect(sideEffect: Unit) {
        // No side effect
    }
}

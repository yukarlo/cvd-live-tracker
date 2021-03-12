package com.yukarlo.ui.country.details

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
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
    BaseFragment<CountryDetailsViewState>(R.layout.country_details_fragment) {

    @Inject
    lateinit var mTextProvider: TextProvider

    private val mViewModel: CountryDetailsViewModel by viewModels()
    private val fragmentBinding: CountryDetailsFragmentBinding by viewBinding(
        CountryDetailsFragmentBinding::bind
    )

    override fun setUpViews() {
        fragmentBinding.countryDetailsCountryImageView.isVisible = true
    }

    override fun setUpObservers() {
        mViewModel.onUiStateUpdated
            .onEach { state -> render(state = state) }
            .launchIn(lifecycleScope)
    }

    override fun render(state: CountryDetailsViewState) {
        with(state.details) {
            fragmentBinding.countryDetailsCountryHeader.text = countryName
            fragmentBinding.countryDetailsActiveCount.text =
                mTextProvider.formatNumber(totalActiveCount)
            fragmentBinding.countryDetailsConfirmedCasesCount.text =
                mTextProvider.formatNumber(totalCasesCount)
            fragmentBinding.countryDetailsDeceasedCount.text =
                mTextProvider.formatNumber(totalDeceasedCount)
            fragmentBinding.countryDetailsRecoveredCount.text =
                mTextProvider.formatNumber(totalRecoveredCount)
            fragmentBinding.countryDetailsCriticalCount.text = mTextProvider.formatNumber(critical)
            fragmentBinding.countryDetailsTestsCount.text = mTextProvider.formatNumber(tests)
            fragmentBinding.countryDetailsCountryImageView.load(countryFlag)
        }
    }
}

package com.yukarlo.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewState : BaseViewState>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
    }

    protected abstract fun setUpViews()
    protected abstract fun setUpObservers()
    protected abstract fun render(state: ViewState)
}
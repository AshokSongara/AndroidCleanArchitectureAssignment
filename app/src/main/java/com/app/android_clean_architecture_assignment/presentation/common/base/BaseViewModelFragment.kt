package com.app.android_clean_architecture_assignment.presentation.common.base


import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper

abstract class BaseViewModelFragment<T : BaseViewModel> : BaseFragment() {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObservers()
        viewModel.loadPage(isMultipleLoad())
    }

    @CallSuper
    protected open fun initLiveDataObservers() {}

    protected open fun isMultipleLoad(): Boolean = false

}
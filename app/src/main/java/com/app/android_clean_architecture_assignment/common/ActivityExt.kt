package com.app.android_clean_architecture_assignment.common

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel

inline fun <reified VM : BaseViewModel> FragmentActivity.initViewModel(
    factory: ViewModelProvider.Factory = defaultViewModelProviderFactory,
): VM {
    return ViewModelProvider(this, factory)[VM::class.java]
}


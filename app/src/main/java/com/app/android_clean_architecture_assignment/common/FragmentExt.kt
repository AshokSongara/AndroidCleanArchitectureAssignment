package com.app.android_clean_architecture_assignment.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel

inline fun <reified VM : BaseViewModel> Fragment.initViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = this,
    factory: ViewModelProvider.Factory = defaultViewModelProviderFactory,
): VM {
    return ViewModelProvider(viewModelStoreOwner, factory)[VM::class.java]
}

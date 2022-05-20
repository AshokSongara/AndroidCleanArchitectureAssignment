package com.app.android_clean_architecture_assignment.presentation.main

import androidx.navigation.findNavController
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseActivity

class MainActivity : BaseActivity() {
    protected val navController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun getContentResource() = R.layout.activity_main

    override fun injectDagger() {
        initScreenComponent()?.inject(this)
    }
}

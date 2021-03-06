package com.app.android_clean_architecture_assignment.presentation.common.base

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.app.android_clean_architecture_assignment.R

abstract class BaseFragment : Fragment(), LifecycleObserver {

    protected abstract fun getContentResource(): Int

    protected abstract fun getClassName(): String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @CallSuper
    protected open fun initViews() {}
}

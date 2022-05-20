package com.app.android_clean_architecture_assignment.presentation.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.android_clean_architecture_assignment.di.presenter.ActivityModule
import com.app.android_clean_architecture_assignment.di.presenter.DaggerPresenterComponent
import com.app.android_clean_architecture_assignment.di.presenter.PresenterComponent
import com.app.android_clean_architecture_assignment.presentation.app.AppController
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun getContentResource(): Int

    protected abstract fun injectDagger()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentResource(), container, false)
    }

    private var presenterComponent: PresenterComponent? = null

    //function for init activity component
    fun initPresenterComponent(): PresenterComponent? {
        if (presenterComponent == null) {
            presenterComponent = DaggerPresenterComponent.builder()
                .appComponent(AppController.instance.appComponent)
                .activityModule(ActivityModule(requireActivity()))
                .build()
        }
        return presenterComponent
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            injectDagger()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    @CallSuper
    protected open fun initViews() {
    }

    protected fun Disposable.collect() = compositeDisposable.add(this)
}

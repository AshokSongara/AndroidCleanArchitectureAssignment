package com.app.android_clean_architecture_assignment.presentation.common.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.android_clean_architecture_assignment.di.presenter.ActivityModule
import com.app.android_clean_architecture_assignment.di.presenter.DaggerPresenterComponent
import com.app.android_clean_architecture_assignment.di.presenter.PresenterComponent
import com.app.android_clean_architecture_assignment.presentation.app.AppController
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()
    private var presenterComponent: PresenterComponent? = null

    protected abstract fun injectDagger()
    protected abstract fun getContentResource(): Int


    //function for init activity component
    fun initScreenComponent(): PresenterComponent? {
        if (presenterComponent == null) {
            presenterComponent = DaggerPresenterComponent.builder()
                .appComponent(AppController.instance.appComponent)
                .activityModule(ActivityModule(this))
                .build()
        }
        return presenterComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        super.onCreate(savedInstanceState)
        setContentView(getContentResource())
        initViews()
    }

    @CallSuper
    protected open fun initViews() {
    }

    protected fun Disposable.collect() = compositeDisposable.add(this)
}
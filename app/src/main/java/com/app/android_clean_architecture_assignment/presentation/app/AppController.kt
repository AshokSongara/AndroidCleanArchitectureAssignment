package com.app.android_clean_architecture_assignment.presentation.app

import android.app.Application
import android.content.Context
import com.app.android_clean_architecture_assignment.BuildConfig
import com.app.android_clean_architecture_assignment.di.app.AppComponent
import com.app.android_clean_architecture_assignment.di.app.AppModule
import com.app.android_clean_architecture_assignment.di.app.DaggerAppComponent
import timber.log.Timber

class AppController : Application() {
    companion object {
        lateinit var instance: AppController
            private set
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
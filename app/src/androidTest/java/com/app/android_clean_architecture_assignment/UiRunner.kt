package com.app.android_clean_architecture_assignment

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class UiRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestAppController::class.java.name, context)
    }
}
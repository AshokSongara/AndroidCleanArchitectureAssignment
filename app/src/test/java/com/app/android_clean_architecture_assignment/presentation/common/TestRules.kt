package com.app.android_clean_architecture_assignment.presentation.common

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

fun instantTaskExecutorRule() {
    ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) {
            runnable.run()
        }

        override fun postToMainThread(runnable: Runnable) {
            runnable.run()
        }

        override fun isMainThread(): Boolean = true
    })
}

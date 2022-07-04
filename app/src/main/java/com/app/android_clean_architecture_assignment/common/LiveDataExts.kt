package com.app.android_clean_architecture_assignment.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.Status
import timber.log.Timber

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T){
    postValue(
        Resource(
            Status.SUCCESS,
            data
        )
    )
}

fun <T> MutableLiveData<Resource<T>>.setLoading() = postValue(Resource(Status.LOADING))

fun <T> MutableLiveData<Resource<T>>.setError(throwable: Throwable? = null) {
    postValue(
        Resource(
            Status.ERROR,
            throwable = throwable
        )
    )
}

fun <T> LiveData<T>.safeObserve(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner) { it?.let(observer) ?: Timber.d("Live data value is null") }
}
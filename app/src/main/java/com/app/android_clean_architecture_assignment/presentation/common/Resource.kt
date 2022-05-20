package com.app.android_clean_architecture_assignment.presentation.common

data class Resource<out T> constructor(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null
)

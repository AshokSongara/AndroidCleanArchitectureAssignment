package com.app.android_clean_architecture_assignment.di.presenter

import android.content.Context
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideRequestManager(@ActivityContext context: Context) = Glide.with(context)
}
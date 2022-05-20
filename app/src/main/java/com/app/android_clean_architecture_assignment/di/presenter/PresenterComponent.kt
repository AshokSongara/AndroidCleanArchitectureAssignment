package com.app.android_clean_architecture_assignment.di.presenter

import com.app.android_clean_architecture_assignment.di.app.AppComponent
import com.app.android_clean_architecture_assignment.di.viewmodel.ViewModelFactoryModule
import com.app.android_clean_architecture_assignment.di.viewmodel.ViewModelModule
import com.app.android_clean_architecture_assignment.presentation.main.MainActivity
import com.app.android_clean_architecture_assignment.presentation.meal.MealFragment
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [PresenterModule::class, ActivityModule::class, ViewModelModule::class, ViewModelFactoryModule::class]
)
@PerPresenter
interface PresenterComponent {
    //injectDagger activity / fragment on here
    fun inject(presenter: MainActivity)
    fun inject(presenter: MealFragment)
}

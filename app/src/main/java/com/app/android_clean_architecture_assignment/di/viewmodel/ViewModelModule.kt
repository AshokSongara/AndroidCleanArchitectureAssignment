package com.app.android_clean_architecture_assignment.di.viewmodel

import androidx.lifecycle.ViewModel
import com.app.android_clean_architecture_assignment.di.app.CommonModule
import com.app.android_clean_architecture_assignment.presentation.meal.MealViewModel
import com.app.android_clean_architecture_assignment.presentation.mealDetail.MealDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// to Inject ViewModel class
@Module(includes = [CommonModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MealViewModel::class)
    abstract fun bindMealViewModel(viewModel: MealViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MealDetailsViewModel::class)
    abstract fun bindMealDetailsViewModel(viewModel: MealDetailsViewModel): ViewModel
}

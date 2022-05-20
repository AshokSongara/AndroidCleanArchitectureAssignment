package com.app.android_clean_architecture_assignment.di.app

import com.app.android_clean_architecture_assignment.di.app.repository.MealModule
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import com.app.android_clean_architecture_assignment.presentation.app.AppController
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, NetworkModule::class, MealModule::class, CommonModule::class]
)
@Singleton
interface AppComponent {
    fun inject(app: AppController)
    fun accountRepository(): MealRepository
}
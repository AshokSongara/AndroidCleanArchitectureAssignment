package com.app.android_clean_architecture_assignment.di.app.repository

import com.app.android_clean_architecture_assignment.data.meal.respository.MealApi
import com.app.android_clean_architecture_assignment.data.meal.respository.MealRepositoryImpl
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MealModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: MealApi): MealRepository {
        return MealRepositoryImpl(api)
    }
}
package com.app.android_clean_architecture_assignment.di

import com.app.android_clean_architecture_assignment.data.remote.respository.MealApi
import com.app.android_clean_architecture_assignment.data.remote.respository.MealRepositoryImpl
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
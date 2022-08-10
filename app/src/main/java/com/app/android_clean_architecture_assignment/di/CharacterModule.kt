package com.app.android_clean_architecture_assignment.di

import com.app.android_clean_architecture_assignment.data.remote.respository.CharacterApi
import com.app.android_clean_architecture_assignment.data.remote.respository.CharacterRepositoryImpl
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: CharacterApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }


}
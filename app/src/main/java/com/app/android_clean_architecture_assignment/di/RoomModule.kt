package com.app.android_clean_architecture_assignment.di

import android.content.Context
import androidx.room.Room
import com.app.android_clean_architecture_assignment.common.AppConstants
import com.app.android_clean_architecture_assignment.data.local.AppDatabase
import com.app.android_clean_architecture_assignment.data.local.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }
}
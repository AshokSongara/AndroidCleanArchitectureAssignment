package com.app.android_clean_architecture_assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.android_clean_architecture_assignment.data.local.dao.MealDao
import com.app.android_clean_architecture_assignment.data.local.model.MealLocal

@Database(
    entities = [MealLocal::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}
package com.app.android_clean_architecture_assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.android_clean_architecture_assignment.data.local.dao.CharacterDao
import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal

@Database(
    entities = [CharacterLocal::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
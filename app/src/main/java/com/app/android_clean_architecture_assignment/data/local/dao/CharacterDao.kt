package com.app.android_clean_architecture_assignment.data.local.dao

import androidx.room.*
import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(character: CharacterLocal)

    @Delete
    fun delete(character: CharacterLocal)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): List<CharacterLocal>
}
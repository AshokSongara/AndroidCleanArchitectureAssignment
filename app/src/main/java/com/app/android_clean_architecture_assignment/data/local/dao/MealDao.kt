package com.app.android_clean_architecture_assignment.data.local.dao

import androidx.room.*
import com.app.android_clean_architecture_assignment.data.local.model.MealLocal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(meals: List<MealLocal>)

    @Delete
    fun delete(meal: MealLocal)

    @Query("SELECT * FROM meals")
    fun getAllMeals(): List<MealLocal>
}
package com.app.android_clean_architecture_assignment.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
class MealLocal(
    @field:PrimaryKey val id: Long,
    @field:ColumnInfo(name = "name") val name: String?,
    @field:ColumnInfo(name = "mealUrl") val mealUrl: String?,
    @field:ColumnInfo(name = "mealDescription") val mealDescription: String?,
)
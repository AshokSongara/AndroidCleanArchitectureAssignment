package com.app.android_clean_architecture_assignment.presentation.mapper

import com.app.android_clean_architecture_assignment.domain.meal.entity.MealEntity
import com.app.android_clean_architecture_assignment.presentation.model.MealModel

class MealDisplayMapper {

    fun transformMealDisplay(mealEntity: MealEntity): MealModel {
        return MealModel(
            mealEntity.name,
            mealEntity.mealUrl
        )
    }
}
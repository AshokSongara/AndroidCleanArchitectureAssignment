package com.app.android_clean_architecture_assignment.presentation.mapper

import com.app.android_clean_architecture_assignment.data.local.model.MealLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.MealEntity
import com.app.android_clean_architecture_assignment.domain.model.MealModel

class MealDisplayMapper {
    fun transformMealDisplay(mealEntity: MealEntity): MealModel {
        return MealModel(
            mealEntity.id,
            mealEntity.name,
            mealEntity.mealUrl,
            mealEntity.mealDescription
        )
    }

    fun transformMealLocalToDomain(mealLocal: MealLocal): MealModel {
        return MealModel(
            mealLocal.id.toString(), mealLocal.name, mealLocal.mealUrl, mealLocal.mealDescription
        )
    }

    fun transformMealDomainToLocal(mealDataItem: MealModel): MealLocal {
        return MealLocal(
            mealDataItem.id.toLong(),
            mealDataItem.name,
            mealDataItem.mealUrl,
            mealDataItem.mealDescription
        )
    }
}
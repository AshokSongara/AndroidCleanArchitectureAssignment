package com.app.android_clean_architecture_assignment.data.remote.respository

import com.app.android_clean_architecture_assignment.data.local.model.MealLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import io.reactivex.Single
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
) : MealRepository {

    override fun getMealData(): Single<MealApiResponse> {
        return mealApi.mealCategory()
    }

    override fun getLocalMealData(): List<MealLocal> {
        TODO("Not yet implemented")
    }

    override fun saveAllLocalData(meals: List<MealLocal>) {
        TODO("Not yet implemented")
    }


}
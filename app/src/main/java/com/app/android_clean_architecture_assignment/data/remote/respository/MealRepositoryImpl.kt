package com.app.android_clean_architecture_assignment.data.remote.respository

import com.app.android_clean_architecture_assignment.data.local.dao.MealDao
import com.app.android_clean_architecture_assignment.data.local.model.MealLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import io.reactivex.Single
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao
) : MealRepository {

    override fun getMealData(): Single<MealApiResponse> {
        return mealApi.mealCategory()
    }

    override fun getLocalMealData(): List<MealLocal> {
        return mealDao.getAllMeals()
    }

    override suspend fun saveAllLocalData(meals: MealLocal) {
        mealDao.insertMeals(meals)
    }

}
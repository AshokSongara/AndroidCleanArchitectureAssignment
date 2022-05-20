package com.app.android_clean_architecture_assignment.presentation.meal

import com.app.android_clean_architecture_assignment.data.meal.entity.MealApiResponse
import com.google.gson.Gson

const val MEAL_JSON = " {\n" +
        "     \"categories\": [{\n" +
        "         \"idCategory\": 1,\n" +
        "         \"strCategoryThumb\": \"https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png\",\n" +
        "         \"strCategory\": \"Beef\"\n" +
        "     }, {\n" +
        "         \"idCategory\": 2,\n" +
        "         \"strCategoryThumb\": \"https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png\",\n" +
        "         \"strCategory\": \"Chicken\"\n" +
        "     }, {\n" +
        "         \"idCategory\": 3,\n" +
        "         \"strCategoryThumb\": \"https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png\",\n" +
        "         \"strCategory\": \"Dessert\"\n" +
        "     }, {\n" +
        "         \"idCategory\": 4,\n" +
        "         \"strCategoryThumb\": \"https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png\",\n" +
        "         \"strCategory\": \"Lamb\"\n" +
        "     }, {\n" +
        "         \"idCategory\": 5,\n" +
        "         \"strCategoryThumb\": \"https:\\/\\/www.themealdb.com\\/images\\/category\\/pasta.png\",\n" +
        "         \"strCategory\": \"Pasta\"\n" +
        "     }]\n" +
        " }"

val gson = Gson()

fun mealResponse(): MealApiResponse =
    gson.fromJson<MealApiResponse>(MEAL_JSON, MealApiResponse::class.java)

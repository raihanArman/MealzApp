package com.raihanarman.mealzapp.model.api

import com.raihanarman.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    suspend fun getMeals(): MealsCategoriesResponse
}
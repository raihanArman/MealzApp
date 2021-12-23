package com.raihanarman.mealzapp.model

import com.raihanarman.mealzapp.model.api.MealsWebService
import com.raihanarman.mealzapp.model.response.Category
import com.raihanarman.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(
    private val webService: MealsWebService = MealsWebService()
) {

    private var cachedMeals = listOf<Category>()

    suspend fun getMeals(): MealsCategoriesResponse{
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): Category?{
        return cachedMeals.firstOrNull{
            it.idCategory == id
        }
    }

    companion object{
        @Volatile
        private var instance: MealsRepository? = null
        fun getInstance() = instance?: synchronized(this){
            instance ?: MealsRepository().also {
                instance = it
            }
        }
    }

}
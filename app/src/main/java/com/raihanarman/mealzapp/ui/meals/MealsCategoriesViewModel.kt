package com.raihanarman.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import com.raihanarman.mealzapp.model.MealsRepository
import com.raihanarman.mealzapp.model.response.Category
import com.raihanarman.mealzapp.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit){
        repository.getMeals{ response ->
            successCallback(response)
        }
    }
}
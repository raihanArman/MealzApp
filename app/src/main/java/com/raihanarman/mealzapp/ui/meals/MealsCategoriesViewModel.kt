package com.raihanarman.mealzapp.ui.meals

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihanarman.mealzapp.model.MealsRepository
import com.raihanarman.mealzapp.model.response.Category
import com.raihanarman.mealzapp.model.response.MealsCategoriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository.getInstance()
): ViewModel() {

    init {
        Log.d("TAG_COROUTINES", ": we are about to launch a coroutines") // 1
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG_COROUTINES", ": we have launched the coroutines") // 3
            val meals = getMeals()
            Log.d("TAG_COROUTINES", ": we have received the async data") // 4
            mealsState.value = meals
        }
        Log.d("TAG_COROUTINES", ": other work") // 2
    }

    val mealsState = mutableStateOf(emptyList<Category>())

    suspend fun getMeals(): List<Category>{
        return repository.getMeals().categories
    }

}
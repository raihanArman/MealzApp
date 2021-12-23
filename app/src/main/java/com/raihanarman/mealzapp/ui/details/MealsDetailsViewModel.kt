package com.raihanarman.mealzapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.raihanarman.mealzapp.model.MealsRepository
import com.raihanarman.mealzapp.model.response.Category

class MealsDetailsViewModel(
    private val saveStateHandle: SavedStateHandle,
): ViewModel() {

    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState = mutableStateOf<Category?>(null)

    init {
        val mealId = saveStateHandle.get<String>("meal_category_id")?: ""

        mealState.value = repository.getMeal(mealId)
    }

}
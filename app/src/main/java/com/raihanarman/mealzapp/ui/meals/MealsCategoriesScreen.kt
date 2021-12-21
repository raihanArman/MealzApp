package com.raihanarman.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.raihanarman.mealzapp.model.response.Category
import com.raihanarman.mealzapp.ui.theme.MealzAppTheme


@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsState.value

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ){
        items(meals){meals->
            MealCategory(meal = meals)
        }
    }
}

@Composable
fun MealCategory(meal: Category){
    var isExpanded by remember{
        mutableStateOf(false)
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ){
        Row(
            modifier = Modifier.animateContentSize()
        ){
            Image(
                painter = rememberImagePainter(meal.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .align(
                        Alignment.CenterVertically
                    )
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text =  meal.strCategory,
                    style = MaterialTheme.typography.h6
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text =  meal.strCategoryDescription,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if(isExpanded) 10 else 3,
                        textAlign = TextAlign.Start
                    )
                }
            }
            Icon(
                imageVector = if(isExpanded)
                    Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier.padding(5.dp)
                    .size(23.dp)
                    .align(
                        if(isExpanded)
                            Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable {
                        isExpanded = !isExpanded
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}
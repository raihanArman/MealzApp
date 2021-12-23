package com.raihanarman.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.raihanarman.mealzapp.model.response.Category
import com.raihanarman.mealzapp.ui.meals.MealsCategoriesScreen
import kotlin.math.max
import kotlin.math.min

@Composable
fun MealsDetailsScreen(meal: Category?){
//    var profilePictureState by remember {
//        mutableStateOf(MealProfilePictureState.Normal)
//    }
//    val transition = updateTransition(targetState = profilePictureState, label = "")

//    val imageSizeDp by transition.animateDp(
//        targetValueByState = {
//            it.size
//        }, label = ""
//    )
//    val color by transition.animateColor(
//        targetValueByState = {
//            it.color
//        }, label = ""
//    )
//    val widthSize by transition.animateDp(
//        targetValueByState = {
//            it.boredrWidth
//        }, label = ""
//    )

//    val scrollState = rememberScrollState()
    val scrollState = rememberLazyListState()
    val offset = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset/600f + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset))

    Surface(
        color = MaterialTheme.colors.background
    ){
        Column{
            Surface(
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ){
                        Image(
                            painter = rememberImagePainter(data = meal?.strCategoryThumb,
                                builder = {
                                    transformations(CircleCropTransformation())
                                },
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size = size)
                                .padding(8.dp)
                        )
                    }
                    Text(
                        text = meal?.strCategory?: "default name" ,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyContentList = (0..100).map{
                it.toString()
            }
            LazyColumn(
                state = scrollState
            ){
                items(dummyContentList){ dummyItem ->
                    Text(text=dummyItem, modifier = Modifier.padding(8.dp))
                }
            }
//            Button(
//                onClick = {
//                      profilePictureState =
//                          if(profilePictureState == MealProfilePictureState.Normal)
//                                MealProfilePictureState.Expanded
//                          else MealProfilePictureState.Normal
//                },
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text("Change state of meal image profile picture")
//            }
        }
    }


}

enum class MealProfilePictureState(val color: Color, val size: Dp, val boredrWidth: Dp){
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}

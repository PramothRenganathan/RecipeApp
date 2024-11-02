package com.example.recipe.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChooseMealScreen(navController: NavController) {

    Text(text = "Main Screen")
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)

    ){
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Choose Your Meal")
            selectionBox("BreakFast")
            selectionBox("Lunch")
            selectionBox("Dinner")
        }
    }

    NextButton(navController = navController, route = "ingredients")
//    Box(modifier = Modifier
//        .fillMaxSize(),
//        contentAlignment = Alignment.BottomCenter
//    ){
//        Button(modifier = Modifier
//            .fillMaxWidth()
//            .padding(12.dp),
//
//            onClick = {
//                navController.navigate("ingredients")
//            }
//        ) {
//
//            Text(text = "Next ->")
//        }
//    }


}

@Composable
fun selectionBox(boxName: String){
    Box (modifier = Modifier
        .size(150.dp)
        .padding(5.dp)
        .border(BorderStroke(2.dp, Color.DarkGray)),
        contentAlignment = Alignment.Center


    ){
        Text(text = boxName)
    }
}

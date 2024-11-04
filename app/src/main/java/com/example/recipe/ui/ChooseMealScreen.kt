package com.example.recipe.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            Text(text = "Choose Your Meal",
                style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(45.dp))
            selectionBox("BreakFast")
            selectionBox("Lunch")
            selectionBox("Dinner")
            NextButton(navController = navController, route = "ingredients")
        }

    }


}

@Composable
fun selectionBox(boxName: String){

    Card (modifier = Modifier
        .size(150.dp)
        .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), // Set the background color to green
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.DarkGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = boxName)
        }
    }
}

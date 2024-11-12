package com.example.recipe.presentation.choose_meal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.recipe.presentation.Screen
import com.example.recipe.presentation.common.NextButton
import com.example.recipe.presentation.viewmodel.SelectionViewModel

@Composable
fun ChooseMealScreen(
    navController: NavController,
    selectionViewModel: SelectionViewModel
) {
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
            selectionBox("BreakFast", onClick = {
                selectionViewModel.addSelection("Breakfast")
            })
            selectionBox("Lunch", onClick = {
                selectionViewModel.addSelection("Lunch")
            })
            selectionBox("Dinner", onClick = {
                selectionViewModel.addSelection("Dinner")
            })
            NextButton(navController = navController, route = Screen.ChooseIngredients.route)
        }

    }


}

@Composable
fun selectionBox(boxName: String, onClick: () -> Unit){

    Card (modifier = Modifier
        .size(150.dp)
        .padding(5.dp)
        .clickable { onClick() },
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

package com.example.recipe.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun IngredientScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        
        val ingredientsList = remember {
            mutableStateListOf<String>()
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
//            IngredientsTopPart()

            IngredientsBottomPart(ingredientsList)

            NextButton(navController = navController, route = "time")

        }
    }

}

@Composable
fun IngredientsTopPart() {
    var expanded by remember { mutableStateOf(false) }

    val ingredientsList = listOf<String>("Onion", "Tomato", "Lettuce")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        Button(modifier = Modifier
            .fillMaxWidth(),
            onClick = {
                expanded = !expanded
            }
        ) {
            Text(text = "Select Ingredients")
        }

        if (expanded) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 50.dp)
            ) {
                items(ingredientsList) { item ->
                    // Each item in the list
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                expanded = false

                            }
                    )
                    Divider()
                }
            }
        }
    }
}



@Composable
fun IngredientsBottomPart(ingredients: MutableList<String>) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.9f)) {

        Log.d("abcdxx", "here "+ingredients.size)
        var ingredientsString = ""
        ingredients.forEach{
            ingredientsString = "$ingredientsString $it "
        }
        Text(text = "Ingredient= $ingredientsString")

        IngredientsSubmitText(ingredients)
    }
}

@Composable
fun IngredientsSubmitText(ingredients: MutableList<String>) {

    var textFieldValue by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Optional padding for better layout
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            modifier = Modifier
                .weight(1f) // Takes up remaining space within the Row
                .padding(end = 8.dp) // Spacing between TextField and Button
        )

        Button(onClick = { ingredients.add(textFieldValue) }) {
            Text(text = "Submit")
        }
    }
}
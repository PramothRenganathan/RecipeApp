package com.example.recipe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TimeScreen(navController: NavController){
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
    ){
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp),
            text = "How many minutes do you have?")
        TimeGrid()
        NextButton(navController = navController, route = "result")
    }

}

@Composable
fun TimeGrid(){

    val cardItems = mutableListOf("15 minutes","30 minutes","45 minutes","1 hour")
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(20.dp)

    ){
        items(cardItems){item ->
            CardItem(item)
        }
    }
}

@Composable
fun CardItem(item: String) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .height(150.dp)
    ) {

        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            Text(text = item)
        }
    }
}
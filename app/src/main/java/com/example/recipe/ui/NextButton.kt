package com.example.recipe.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
    fun NextButton(navController: NavController, route:String){
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),

                onClick = {
                    navController.navigate(route)
                }
            ) {
                Text(text = "Next ->")
            }
        }
    }
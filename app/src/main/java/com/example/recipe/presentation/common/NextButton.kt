package com.example.recipe.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
    fun NextButton(navController: NavController, route:String){
        Box(modifier = Modifier,
            contentAlignment = Alignment.BottomCenter
        ){
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer), // Set the background color to green
                onClick = {

                    navController.navigate(route)
                }
            ) {
                Text(text = "Next ->",
                    style = MaterialTheme.typography.headlineSmall,
                    )
            }
        }
    }
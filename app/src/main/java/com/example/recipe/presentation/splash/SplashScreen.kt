package com.example.recipe.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.recipe.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit){
        delay(1000)
        navController.navigate("meal") {
            popUpTo("splash") { inclusive = true } // Remove splash screen from the back stack
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
    ){

        Image(painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash screen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

    }
}


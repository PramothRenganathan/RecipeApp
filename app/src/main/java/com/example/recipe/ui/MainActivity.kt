package com.example.recipe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination ="splash" ){
        composable("splash") {
            SplashScreen(navController)
        }
        composable("meal") {
            ChooseMealScreen(navController)
        }
        composable("ingredients") {
            IngredientScreen(navController)
        }
        composable("time") {
            TimeScreen()
        }
    }
}

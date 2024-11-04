package com.example.recipe.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe.presentation.choose_Ingredients.IngredientScreen
import com.example.recipe.presentation.choose_meal.ChooseMealScreen
import com.example.recipe.presentation.choose_time.TimeScreen
import com.example.recipe.presentation.instructions.ResultScreen
import com.example.recipe.presentation.theme.AppTheme
import com.example.recipe.presentation.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // State to track whether permission is granted
        var permissionGranted by mutableStateOf(false)

        // Register a permission launcher
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            permissionGranted = isGranted
        }

        // Check if the permission is already granted; if not, request it
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }


        setContent {

            AppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    RequestAudioPermission(permissionGranted, onRequestPermission = {
//                        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
//                    })
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screen.ShowSplash.route ){
        composable(Screen.ShowSplash.route) {
            SplashScreen(navController)
        }
        composable(Screen.ChooseMealsScreen.route) {
            ChooseMealScreen(navController)
        }
        composable(Screen.ChooseIngredients.route) {
            IngredientScreen(navController)
        }
        composable(Screen.ChooseTime.route) {
            TimeScreen(navController)
        }
        composable(Screen.ShowResult.route) {
            ResultScreen()
        }
    }
}

@Composable
fun RequestAudioPermission(permissionGranted: Boolean, onRequestPermission: () -> Unit) {
    Box {
        if (permissionGranted) {
            Button(onClick = {

                // Handle the action when permission is granted
            }) {
                Text(text = "Record Audio")
            }
        } else {
            Button(onClick = onRequestPermission) {
                Text(text = "Request Audio Permission")
            }
        }
    }
}

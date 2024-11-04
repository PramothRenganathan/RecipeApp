package com.example.recipe.ui

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
import com.example.recipe.ui.theme.AppTheme

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
    
    NavHost(navController = navController, startDestination ="splash" ){
        composable("splash") {
            SplashScreen(navController)
//            RecordAudioScreen()
//            SpeechToTextScreen()
        }
        composable("meal") {
            ChooseMealScreen(navController)
        }
        composable("ingredients") {
            IngredientScreen(navController)
        }
        composable("time") {
            TimeScreen(navController)
        }
        composable("result") {
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

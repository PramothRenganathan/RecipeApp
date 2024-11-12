package com.example.recipe.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe.presentation.choose_Ingredients.IngredientScreen
import com.example.recipe.presentation.choose_meal.ChooseMealScreen
import com.example.recipe.presentation.choose_time.TimeScreen
import com.example.recipe.presentation.recipe_instructions.ResultScreen
import com.example.recipe.presentation.splash.SplashScreen
import com.example.recipe.presentation.viewmodel.SelectionViewModel


@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    val selectionViewModel: SelectionViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.ShowSplash.route ){
        composable(Screen.ShowSplash.route) {
            SplashScreen(navController)
        }
        composable(Screen.ChooseMealsScreen.route) {
            ChooseMealScreen(navController, selectionViewModel)
        }
        composable(Screen.ChooseIngredients.route) {
            IngredientScreen(navController, selectionViewModel)
        }
        composable(Screen.ChooseTime.route) {
            TimeScreen(navController, selectionViewModel)
        }
        composable(Screen.ShowResult.route) {
            ResultScreen(selectionViewModel)
        }
    }
}

package com.example.recipe.presentation

sealed class Screen(val route: String) {
    data object ShowSplash : Screen("Splash")
    data object ChooseMealsScreen : Screen("meal")
    data object ChooseIngredients : Screen("ingredients")
    data object ChooseTime : Screen("time")
    data object ShowResult : Screen("result")
    data object ChooseAllergies : Screen("allergies")
}
package com.example.recipe.presentation.recipe_instructions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.recipe.presentation.viewmodel.SelectionViewModel

@Composable
fun ResultScreen(
    selectionViewModel: SelectionViewModel
) {
    Text(text = "Result = "+selectionViewModel.selections[0])

}
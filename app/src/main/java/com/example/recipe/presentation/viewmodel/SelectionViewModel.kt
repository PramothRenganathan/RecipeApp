package com.example.recipe.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor() : ViewModel() {

    // A list to hold selections, or use specific variables for each screen if needed
    private val _selections = mutableStateOf<List<String>>(emptyList())
    val selections: List<String> get() = _selections.value

    // Method to add a selection
    fun addSelection(selection: String) {
        _selections.value = _selections.value + selection

    }
}
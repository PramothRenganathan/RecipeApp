package com.example.recipe.presentation.choose_Ingredients

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipe.presentation.common.NextButton

@Composable
fun IngredientScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(),
        ) {
        val ingredientsListFromTyping = remember { mutableStateOf("") }
        val ingredientsListFromSpeech = remember { mutableStateOf("") }
        val consolidatedIngredients = remember { mutableStateListOf<String>() }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            IngredientsDisplay(ingredientsListFromTyping, ingredientsListFromSpeech, consolidatedIngredients)

            // Spacer that takes up remaining space
//            Spacer(modifier = Modifier.weight(1f)) // Pushes the next item to the bottom



            SpeechToText(ingredientsListFromSpeech)
            // Text field for submitting ingredients
            IngredientsSubmitText(onIngredientSubmit = { newIngredient ->
                ingredientsListFromTyping.value = newIngredient
            })
            NextButton(navController = navController, route = "time")
        }
    }
}

@Composable
fun IngredientsDisplay(
    ingredientsListFromTyping: MutableState<String>,
    ingredientsListFromSpeech: MutableState<String>,
    consolidatedIngredients: MutableList<String>
) {
        val ingredientsString = ingredientsListFromTyping.value

//    Log.d("abcdxx", "IngredientsDisplay: ingredientsString: $ingredientsString ingredientsListFromSpeech: ${ingredientsListFromSpeech.value}")
    if (!consolidatedIngredients.contains(ingredientsListFromTyping.value)) {
        consolidatedIngredients.add(ingredientsListFromTyping.value)
    }
    if (!consolidatedIngredients.contains(ingredientsListFromSpeech.value)) {
        consolidatedIngredients.add(ingredientsListFromSpeech.value)
    }

    val result = consolidatedIngredients.joinToString(" ")

//    Text(text = "Ingredients: $ingredientsString ${ingredientsListFromSpeech.value} ")
    Text(text = "Ingredients: $result ")
}

@Composable
fun IngredientsSubmitText(onIngredientSubmit: (String) -> Unit) {
    var textFieldValue by remember { mutableStateOf("") }

    // Use a Row to layout the TextField and Button
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding() // Ensures the Row is pushed up when the keyboard is open
            .padding(16.dp), // Optional padding for better layout
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            modifier = Modifier
                .weight(1f) // Takes up remaining space within the Row
                .padding(end = 8.dp) // Spacing between TextField and Button
        )

        Button(onClick = {
            if (textFieldValue.isNotBlank()) {
                onIngredientSubmit(textFieldValue.trim())
                textFieldValue = "" // Clear the text field after submission
            }
        }) {
            Text(text = "Submit")
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SpeechToText(ingredientsListFromSpeech: MutableState<String>) {
    var speechText by remember { mutableStateOf("Press and hold the button to start speaking") }
    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    var isEndOfSpeech = false;

    // Intent for speech recognition
    val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
    }

    // Set up recognition listener
    DisposableEffect(Unit) {
        val listener = object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Log.d("abcdxx", "onReadyForSpeech: ")
                speechText = "start Speaking .."
            }
            override fun onBeginningOfSpeech() {
                Log.d("abcdxx", "onBeginningOfSpeech: ")
                speechText = "Listening..."
            }
            override fun onRmsChanged(rmsdB: Float) {
                Log.d("abcdxx", "onRmsChanged: ")
            }
            override fun onBufferReceived(buffer: ByteArray?) {
                Log.d("abcdxx", "onBufferReceived: ")
            }
            override fun onEndOfSpeech() {
                Log.d("abcdxx", "onEndOfSpeech: ")
                isEndOfSpeech = true;
            }
            override fun onError(error: Int) {
                Log.d("abcdxx", "onError: ")
                speechText = "Error recognizing speech $error"
                if (!isEndOfSpeech)
                    return;
            }

            override fun onResults(results: Bundle?) {
                Log.d("abcdxx", "onResults: ")
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    speechText = matches[0] // Get the first result
                    ingredientsListFromSpeech.value = speechText
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        }

        speechRecognizer.setRecognitionListener(listener)

        onDispose {
            speechRecognizer.destroy()
        }
    }

    // Start and stop listening on button press
    fun startListening() {
        speechRecognizer.startListening(recognizerIntent)
    }

    fun stopListening() {
        speechRecognizer.stopListening()
    }

    // UI Layout
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Floating Action Button with press and release detection
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .size(56.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {

                            startListening()
                            true
                        }

                        MotionEvent.ACTION_UP -> {
                            stopListening()
                            true
                        }

                        else -> false
                    }
                }
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Record Audio"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the recognized text
        Text(
            text = speechText,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
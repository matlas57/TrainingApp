package com.example.trainingapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreen.Today)
    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    var exerciseTitleState by mutableStateOf("")
    var exerciseSetsState by mutableStateOf("")
    var exerciseRepsState by mutableStateOf("")
    var exerciseWeightState by mutableStateOf("")
}
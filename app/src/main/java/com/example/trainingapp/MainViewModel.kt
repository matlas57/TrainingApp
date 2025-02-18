package com.example.trainingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingapp.model.Exercise
import com.example.trainingapp.model.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(
    private val exerciseRepository: ExerciseRepository = Graph.exerciseRepository
): ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreen.Today)
    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    var exerciseTitleState by mutableStateOf("")
    var exerciseSetsState by mutableStateOf("")
    var exerciseRepsState by mutableStateOf("")
    var exerciseWeightState by mutableStateOf("")

    lateinit var getAllExercises: Flow<List<Exercise>>
    lateinit var getTodaysExercises: Flow<List<Exercise>>

    init {
        viewModelScope.launch {
            getAllExercises = exerciseRepository.getAllExercises()
        }
    }

    init {
        viewModelScope.launch {
            getTodaysExercises = exerciseRepository.getTodaysExercises(LocalDate.now().toString())
        }
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.addExercise(exercise)
        }
    }

    fun getExerciseById(id: Long): Flow<Exercise> = exerciseRepository.getExerciseById(id)

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.updateExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.deleteExercise(exercise)
        }
    }
}
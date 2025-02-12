package com.example.trainingapp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.trainingapp.MainViewModel
import com.example.trainingapp.model.Exercise
import com.example.trainingapp.model.WeightUnit
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseView(
    viewModel: MainViewModel,
    dialogOpen: MutableState<Boolean>
){
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.inverseSurface),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            content = {
                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Add Exercise",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    OutlinedTextField(
                        value = viewModel.exerciseTitleState,
                        onValueChange = {
                            viewModel.exerciseTitleState = it
                        },
                        modifier = Modifier.padding(top = 16.dp),
                        label = {
                            Text("Exercise Name", color = MaterialTheme.colorScheme.inverseSurface)
                        }
                    )
                    Row (
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        OutlinedTextField(
                            value = viewModel.exerciseSetsState,
                            onValueChange = {
                                viewModel.exerciseSetsState = it
                            },
                            modifier = Modifier.padding(top = 16.dp).padding(end = 32.dp).width(100.dp),
                            label = {
                                Text("Sets", color = MaterialTheme.colorScheme.inverseSurface)
                            }
                        )
                        OutlinedTextField(
                            value = viewModel.exerciseRepsState,
                            onValueChange = {
                                viewModel.exerciseRepsState = it
                            },
                            modifier = Modifier.padding(top = 16.dp).width(100.dp),
                            label = {
                                Text("Reps", color = MaterialTheme.colorScheme.inverseSurface)
                            }
                        )
                    }
                    OutlinedTextField(
                        value = viewModel.exerciseWeightState,
                        onValueChange = {
                            viewModel.exerciseWeightState = it
                        },
                        modifier = Modifier.padding(top = 16.dp),
                        label = {
                            Text("Weight", color = MaterialTheme.colorScheme.inverseSurface)
                        }
                    )
                    Button(
                        onClick = {
                            viewModel.addExercise(
                                Exercise(
                                    date = LocalDate.now().toString(),
                                    name = viewModel.exerciseTitleState.trim(),
                                    sets = viewModel.exerciseSetsState.toIntOrNull() ?: 0,
                                    reps = viewModel.exerciseRepsState.toIntOrNull() ?: 0,
                                    weight = viewModel.exerciseWeightState.toDoubleOrNull() ?: 0.0,
                                    weightUnit = WeightUnit.LBS
                                )
                            )
                            dialogOpen.value = false
                        },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6200EE)
                        )
                    ) {
                        Text(
                            text = "Add",
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                }
            },
        )
    }
}
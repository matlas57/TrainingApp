package com.example.trainingapp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trainingapp.MainViewModel
import com.example.trainingapp.model.Exercise

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayView(viewModel: MainViewModel) {
    val todaysExerciseList = viewModel.getTodaysExercises.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        List exercises logged today
        items(todaysExerciseList.value, key = {exercise -> exercise.id}) { exercise ->

            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd) {
                        viewModel.deleteExercise(exercise)
                    }
                    true
                }
            )

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = { FractionalThreshold(0.25f) },
                background = {
                    val color by animateColorAsState(
                        if (dismissState.dismissDirection == DismissDirection.StartToEnd) {
                            Color.Red
                        }
                        else {
                            Color.Transparent
                        },
                        label = ""
                    )
                    Box (
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                            .background(color),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Icon",
                            tint = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                },
                dismissContent = {
                    TodayViewItem(exercise)
                }
            )
        }
    }
}

@Composable
fun TodayViewItem(exercise: Exercise) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp)
        ){
            Text(text = exercise.name)
        }
        Row (
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = "Sets: ${exercise.sets} | Reps: ${exercise.reps} | Weight: ${exercise.weight}${exercise.weightUnit.name.lowercase()}")
        }
        Divider(color = Color.LightGray)
    }
}

package com.example.trainingapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trainingapp.model.Exercise
import com.example.trainingapp.model.WeightUnit
import com.example.trainingapp.model.sampleData

@Composable
fun TodayView() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        //List exercises logged today
        items(sampleData) { item ->
            TodayViewItem(item)
        }
    }
}

@Composable
fun TodayViewItem(exercise: Exercise) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.padding(bottom = 16.dp).padding(horizontal = 16.dp)
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

@Preview
@Composable
fun TodayViewItemComposable() {
    val e = Exercise("Bench Press", 3, 4, 185.0, WeightUnit.LBS)
    TodayViewItem(e)
}
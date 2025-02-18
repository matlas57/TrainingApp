package com.example.trainingapp.view

import android.content.ClipData.Item
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainingapp.MainViewModel
import com.example.trainingapp.model.Exercise

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LogView(viewModel: MainViewModel) {
    val exerciseList = viewModel.getAllExercises.collectAsState(initial = listOf())
    val groupedByDate: Map<String, List<Exercise>> = exerciseList.value.groupBy { it.date }
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        groupedByDate.forEach{
            (date, exercises) ->
            item {
                DayBlock(date, exercises)
            }
        }
    }
}

@Composable
fun DayBlock(date: String, exercises: List<Exercise>){
    Column {
        Text(
            modifier = Modifier.padding(top = 8.dp).padding(bottom = 4.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = date
        )
        exercises.forEach {
            LogViewItem(it)
        }
    }
}

@Composable
fun LogViewItem(exercise: Exercise) {
    Column (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Row (
            modifier = Modifier.padding(bottom = 8.dp)
        ){
            Text(text = exercise.name)
        }
        Row (
            modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 8.dp)
        ) {
            Text(text = "Sets: ${exercise.sets} | Reps: ${exercise.reps} | Weight: ${exercise.weight}${exercise.weightUnit.name.lowercase()}")
        }
        Divider(color = Color.LightGray)
    }
}

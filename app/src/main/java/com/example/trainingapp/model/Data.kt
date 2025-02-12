package com.example.trainingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sets")
    val sets: Int,
    @ColumnInfo(name = "reps")
    val reps: Int,
    @ColumnInfo(name = "weight")
    val weight: Double,
    @ColumnInfo(name = "unit")
    val weightUnit: WeightUnit
)

enum class WeightUnit {
    KG,
    LBS
}

//val sampleData = listOf<Exercise>(
//    Exercise("Bench Press", 3, 4, 185.0, WeightUnit.LBS),
//    Exercise("Squat", 3, 6, 200.0, WeightUnit.LBS),
//    Exercise("Deadlift", 3, 2, 250.0, WeightUnit.LBS),
//    Exercise("Overhead Press", 3, 4, 100.0, WeightUnit.LBS),
//    Exercise("Bicep Curl", 3, 4, 40.0, WeightUnit.LBS)
//)
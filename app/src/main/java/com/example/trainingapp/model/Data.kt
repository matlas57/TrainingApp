package com.example.trainingapp.model

data class Exercise (
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Double,
    val weightUnit: WeightUnit
)

enum class WeightUnit {
    KG,
    LBS
}

val sampleData = listOf<Exercise>(
    Exercise("Bench Press", 3, 4, 185.0, WeightUnit.LBS),
    Exercise("Squat", 3, 6, 200.0, WeightUnit.LBS),
    Exercise("Deadlift", 3, 2, 250.0, WeightUnit.LBS),
    Exercise("Overhead Press", 3, 4, 100.0, WeightUnit.LBS),
    Exercise("Bicep Curl", 3, 4, 40.0, WeightUnit.LBS)
)
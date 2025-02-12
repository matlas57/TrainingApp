package com.example.trainingapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Exercise::class],
    version = 1,
    exportSchema = false
)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}
package com.example.trainingapp

import android.content.Context
import androidx.room.Room
import com.example.trainingapp.model.ExerciseDatabase
import com.example.trainingapp.model.ExerciseRepository

object Graph {
    lateinit var database: ExerciseDatabase

    val exerciseRepository by lazy {
        ExerciseRepository(exerciseDao = database.exerciseDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            ExerciseDatabase::class.java,
            "exerciselist.db"
        ).build()
    }
}
package com.example.trainingapp.model

import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    suspend fun addExercise(exercise: Exercise){
        exerciseDao.addExercise(exercise)
    }

    fun getTodaysExercises(today: String): Flow<List<Exercise>> = exerciseDao.getTodaysExercises(today)

    fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    fun getExerciseById(id: Long): Flow<Exercise> = exerciseDao.getExerciseById(id)

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }
}
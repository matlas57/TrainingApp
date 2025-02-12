package com.example.trainingapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addExercise(exerciseEntity: Exercise)

    @Query("Select * from `exercise_table` where date=:today")
    abstract fun getTodaysExercises(today: String): Flow<List<Exercise>>

    @Query("Select * from `exercise_table`")
    abstract fun getAllExercises(): Flow<List<Exercise>>

    @Query("Select * from `exercise_table` where id=:id")
    abstract fun getExerciseById(id: Long): Flow<Exercise>

    @Update
    abstract suspend fun updateExercise(exerciseEntity: Exercise)

    @Delete
    abstract suspend fun deleteExercise(exerciseEntity: Exercise)
}
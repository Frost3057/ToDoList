package com.example.todolist.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(task: Task)

    @Delete
    suspend fun deleteNote(task: Task)

    @Query("Select * from tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("Select * from tasks where id = id")
    suspend fun getTaskbyId(id:Int): Task
}
package com.example.todolist.Domain

import com.example.todolist.Data.Task
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface TaskRepo {
    fun getTasks(): Flow<List<Task>>;
    suspend fun deleteTask(task: Task);
    suspend fun getTaskById(id:Int) : Task?;
    suspend fun insert(task: Task);
}
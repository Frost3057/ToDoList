package com.example.todolist.Data

import com.example.todolist.Domain.TaskRepo
import kotlinx.coroutines.flow.Flow

class TaskRepoImpl(
    private val taskDAO: TaskDAO
): TaskRepo{
    override fun getTasks(): Flow<List<Task>> {
        return taskDAO.getAllTasks()
    }
    override suspend fun deleteTask(task: Task) {
        taskDAO.deleteNote(task)
    }
    override suspend fun getTaskById(id: Int): Task{
        return taskDAO.getTaskbyId(id)
    }
    override suspend fun insert(task: Task) {
        taskDAO.insertNote(task)
    }
}
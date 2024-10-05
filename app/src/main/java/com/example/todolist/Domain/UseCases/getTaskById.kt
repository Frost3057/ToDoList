package com.example.todolist.Domain.UseCases

import androidx.annotation.Nullable
import com.example.todolist.Data.Task
import com.example.todolist.Domain.TaskRepo

class getTaskById (
    private val taskRepo: TaskRepo
){
    suspend operator fun invoke(idx : Long): Task?{
        return taskRepo.getTaskById(idx)
    }
}
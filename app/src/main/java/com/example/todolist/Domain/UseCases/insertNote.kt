package com.example.todolist.Domain.UseCases

import com.example.todolist.Data.Task
import com.example.todolist.Domain.TaskRepo

class insertTask(
    private val taskRepo: TaskRepo
) {
    suspend operator fun invoke(task: Task){
        taskRepo.insert(task)
    }
}
package com.example.todolist.Presentation.ViewModels.Home

import com.example.todolist.Data.Task

sealed class TasksEvent {
    data class delete(val task: Task): TasksEvent()
}
package com.example.todolist.Domain.UseCases

data class UseCases(
    val deleteNote: deleteNote,
    val getTask: getNotes,
    val insertTask: insertTask,
    val getTaskById: getTaskById
)
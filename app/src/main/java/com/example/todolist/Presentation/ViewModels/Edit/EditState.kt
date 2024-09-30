package com.example.todolist.Presentation.ViewModels.Edit

data class EditState(
    val title : String="",
    val hint : String ="",
    val isHintShown: Boolean = true
)
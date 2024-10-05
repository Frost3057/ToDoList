package com.example.todolist.Presentation.ViewModels.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Domain.UseCases.UseCases
import com.example.todolist.Domain.Util.basisof
import com.example.todolist.Domain.Util.orderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class homeviewModel @Inject constructor(
        private val useCases: UseCases
) : ViewModel(){

    fun refreshTasks() {
        viewModelScope.launch {
            useCases.getTask().collect { tasks ->
                _state.value = state.value.copy(tasks = tasks)
            }
        }
    }

    private val _state = mutableStateOf(TASKSTATE())
    val state:State<TASKSTATE> = _state
    init{
        viewModelScope.launch {
            useCases.getTask().collect{
                tasks->
                _state.value = state.value.copy(
                    tasks = tasks
                )
            }
        }
    }
    fun onEvent(event: TasksEvent){
        when(event){
            is TasksEvent.delete -> {
                viewModelScope.launch {
                    useCases.deleteNote(event.task)
                    println("deletePRESSED")
                }
            }
        }

    }
}

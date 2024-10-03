package com.example.todolist.Presentation.ViewModels.Edit

import android.os.Message
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.Task
import com.example.todolist.Domain.UseCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class editViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _TitleState = mutableStateOf(EditState())
    val titleState: State<EditState> = _TitleState

    private val _ContentState = mutableStateOf(EditState())
    val contentState: State<EditState> = _ContentState

    private val _uiState = MutableSharedFlow<UIevent>()
    val uiState = _uiState.asSharedFlow()

    private var _taskId: Int? =null

    init {
     savedStateHandle.get<Int>("taskid")?.let {
         id->
         if(id!=-1){
             viewModelScope.launch {
                 useCases.getTaskById(id)?.also {
                     task->
                     _taskId = id
                     _TitleState.value = titleState.value.copy(
                         title = task.title,
                         isHintShown = false
                     )
                     _ContentState.value = contentState.value.copy(
                         title = task.description,
                         isHintShown = false
                     )

                 }
             }
         }
     }
    }

    fun onEvent(event: EditEvent){
        when(event){
            EditEvent.Save -> {
                viewModelScope.launch {
                    try {
                        _taskId?.let {
                            Task(
                                id = it,
                                description = contentState.value.title,
                                title = titleState.value.title,
                                timeStamp = System.currentTimeMillis()
                                )
                        }?.let {
                            useCases.insertTask(
                                it
                            )
                        }
                    }catch (e : Exception){
                        _uiState.emit(
                            UIevent.showSnackBar(e.message.toString())
                        )
                    }
                }
            }
            is EditEvent.changeDescription -> {
                _ContentState.value = contentState.value.copy(
                    title = event.Description
                )
            }
            is EditEvent.changeDescriptionFocus -> {
                _ContentState.value = contentState.value.copy(
                    isHintShown = !event.focus.isFocused&&contentState.value.title.isBlank()
                )
            }
            is EditEvent.changeTitle -> {
                _TitleState.value = titleState.value.copy(
                    title = event.Title
                )
            }
            is EditEvent.changeTitleFocus -> {
                _TitleState.value = titleState.value.copy(
                    isHintShown = !event.focus.isFocused&&titleState.value.title.isBlank()
                )
            }
        }
    }
}

sealed class UIevent{
    data class showSnackBar(val message: String) : UIevent()
    object save : UIevent()
}
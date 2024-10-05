package com.example.todolist.Presentation.ViewModels.Edit

import android.os.Message
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Data.InvalidTaskException
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

    private var _taskId: Long = -1

    init {
     savedStateHandle.get<Long>("taskid")?.let {
         id->
         if(id.toInt() !=-1){
             viewModelScope.launch {
                 useCases.getTaskById(id)?.also {
                     task->
                     _taskId = id.toLong()
                     _TitleState.value = _TitleState.value.copy(
                         title = task.title,
                         isHintShown = false
                     )
                     _ContentState.value = _ContentState.value.copy(
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
                    try{
                        Task(
                            id = 0,
                            description = contentState.value.title,
                            title = titleState.value.title,
                            timeStamp = System.currentTimeMillis()
                        ).let {
                            useCases.insertTask(
                                it
                            )
                        }
                        _uiState.emit(UIevent.save)//this was not emitting due to which our app was not working

                    }catch (e : Exception){
                        _uiState.emit(
                            UIevent.showSnackBar(message = e.message?:"Couldn't save note")
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
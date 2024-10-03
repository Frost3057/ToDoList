package com.example.todolist.Presentation.Compoasables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolist.Presentation.Compoasables.EditScreenUtil.TransperentFieldBox
import com.example.todolist.Presentation.ViewModels.Edit.EditEvent
import com.example.todolist.Presentation.ViewModels.Edit.UIevent
import com.example.todolist.Presentation.ViewModels.Edit.editViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun addeditScreen(
    viewModel: editViewModel = hiltViewModel(),
    navController: NavController
) {
    val titleState = viewModel.titleState
    val contentState = viewModel.contentState

    val scafoldstate = rememberBottomSheetScaffoldState()

    LaunchedEffect(key1 = 1) {
        viewModel.uiState.collectLatest {
            event->
            when(event){
                UIevent.save -> navController.navigateUp()
                is UIevent.showSnackBar -> scafoldstate.snackbarHostState.showSnackbar(event.message)
            }
        }
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {viewModel.onEvent(EditEvent.Save)}
        ) {
            Icon(Icons.Default.Edit,"Save")
        }
    }, modifier = Modifier.fillMaxSize(), snackbarHost = { SnackbarHost(scafoldstate.snackbarHostState) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            TransperentFieldBox(
                titleState.value.title,
                isHintShown = titleState.value.isHintShown,
                modifier = Modifier, onFocusChange = {
                        viewModel.onEvent(EditEvent.changeTitleFocus(it))
                },
                singleLine = true, onContentChange = {
                    viewModel.onEvent(EditEvent.changeTitle(it))
                }
            )
            Spacer(modifier = Modifier.padding(16.dp))
            TransperentFieldBox(
                contentState.value.title,
                isHintShown = contentState.value.isHintShown,
                modifier = Modifier, onFocusChange = {
                    viewModel.onEvent(EditEvent.changeDescriptionFocus(it))
                },
                singleLine = true, onContentChange = {
                    viewModel.onEvent(EditEvent.changeDescription(it))
                }
            )
        }
    }
}
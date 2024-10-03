package com.example.todolist.Presentation.Compoasables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Presentation.Compoasables.HomeScreenUtil.taskComp
import com.example.todolist.Presentation.ViewModels.Home.TasksEvent
import com.example.todolist.Presentation.ViewModels.Home.homeviewModel
import androidx.hilt.navigation.compose.hiltViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun homeScreen(
    navController: NavController,
    viewModel: homeviewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {

            },
                containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            LazyColumn {
                items(state.tasks){
                    task->
                    taskComp(
                        task = task,
                        checked = task.isChecked,
                        onCheckAction = {
                            viewModel.onEvent(event = TasksEvent.delete(task = task))
                        }
                    )
                }
            }
        }
    }
}
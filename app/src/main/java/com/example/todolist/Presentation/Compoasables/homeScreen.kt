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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Presentation.Compoasables.HomeScreenUtil.taskComp
import com.example.todolist.Presentation.ViewModels.Home.TasksEvent
import com.example.todolist.Presentation.ViewModels.Home.homeviewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.util.routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun homeScreen(
    navController: NavController,
    viewModel: homeviewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    println("Number of tasks: ${state.tasks.size}")
    //this refreshes the screen after every entry of task....
    LaunchedEffect(key1 = true) {
        navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>("refresh")?.let { shouldRefresh ->
            if (shouldRefresh) {
                viewModel.refreshTasks()
                navController.currentBackStackEntry?.savedStateHandle?.remove<Boolean>("refresh")
            }
        }
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = routes.addEditScreen.string)
            },
                containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {

            Text("Tasks", modifier = Modifier.padding(top = 54.dp), style = MaterialTheme.typography.headlineLarge)
            LazyColumn {
                items(state.tasks){
                    task->
                    taskComp(
                        task = task,
                        checked = task.isChecked,
                        onCheckAction = {
                            task.isChecked = it
                            viewModel.onEvent(event = TasksEvent.delete(task = task))
                        }
                    )
                }
            }
        }
    }
}


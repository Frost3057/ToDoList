package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.Presentation.Compoasables.addeditScreen
import com.example.todolist.Presentation.Compoasables.homeScreen
import com.example.todolist.ui.theme.ToDoListTheme
import com.example.todolist.util.routes
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                Surface (
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = routes.homeScreen.string) {
                        composable(route = routes.homeScreen.string) {
                            homeScreen(navController)
                        }
                        composable(route = routes.addEditScreen.string+"?id = {id}", arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )) {
                            addeditScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

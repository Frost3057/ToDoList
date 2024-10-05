package com.example.todolist.util

sealed class routes(val string: String){
    object homeScreen : routes("homeScreen")
    object addEditScreen : routes("addEditScreen")
}
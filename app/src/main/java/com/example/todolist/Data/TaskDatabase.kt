package com.example.todolist.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class],
    version = 1)
abstract class TaskDatabase:RoomDatabase() {
    abstract val taskDAO:TaskDAO

    companion object{
        val database = "database"
    }
}
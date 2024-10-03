package com.example.todolist.Data

import android.app.ActivityManager.TaskDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id : Int =0,
    val title:String,
    val description: String,
    val isChecked : Boolean = false,
    val timeStamp : Long
    )

package com.example.todolist.DI

import android.app.Application
import androidx.core.app.AppLocalesStorageHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.Data.Task
import com.example.todolist.Data.TaskDatabase
import com.example.todolist.Data.TaskRepoImpl
import com.example.todolist.Domain.TaskRepo
import com.example.todolist.Domain.UseCases.UseCases
import com.example.todolist.Domain.UseCases.deleteNote
import com.example.todolist.Domain.UseCases.getNotes
import com.example.todolist.Domain.UseCases.getTaskById
import com.example.todolist.Domain.UseCases.insertTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {
    @Provides
    @Singleton
    fun provideDatabase(app:Application):TaskDatabase{
        return Room.databaseBuilder(app,TaskDatabase::class.java,"database").build()
    }
    @Provides
    @Singleton
    fun provideRepo(
        database: TaskDatabase
    ):TaskRepo{
        return TaskRepoImpl(database.taskDAO)
    }

    @Provides
    @Singleton
    fun provideRepoImpl(
        repo: TaskRepo
    ):UseCases{
        return UseCases(
            deleteNote = deleteNote(repo),
            getTask = getNotes(repo),
            getTaskById = getTaskById(repo),
            insertTask = insertTask(repo)
        )
    }
}
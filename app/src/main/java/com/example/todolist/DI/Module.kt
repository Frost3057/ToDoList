package com.example.todolist.DI

import android.app.Application
import androidx.core.app.AppLocalesStorageHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.Data.Task
import com.example.todolist.Data.TaskDatabase
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
}
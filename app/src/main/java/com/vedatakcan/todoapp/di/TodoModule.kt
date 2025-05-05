package com.vedatakcan.todoapp.di

import android.app.Application
import androidx.compose.ui.graphics.findFirstCubicRoot
import androidx.room.Room
import com.vedatakcan.todoapp.data.database.TodoDatabase
import com.vedatakcan.todoapp.data.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "todos"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDatabase: TodoDatabase): TodoRepository {
        return TodoRepository(todoDatabase.todoDao())
    }
}
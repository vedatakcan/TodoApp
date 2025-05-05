package com.vedatakcan.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vedatakcan.todoapp.data.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}